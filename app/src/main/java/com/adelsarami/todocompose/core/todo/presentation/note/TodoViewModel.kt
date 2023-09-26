package com.adelsarami.todocompose.core.todo.presentation.note

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.adelsarami.todocompose.core.todo.data.TodoRepository
import com.adelsarami.todocompose.core.todo.data.model.TodoModel
import com.adelsarami.todocompose.util.base.BaseViewModel
import com.adelsarami.todocompose.util.ui.component.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    init {
        savedStateHandle.get<Int>("todoId")?.let {
            if (it != -1) {
                getTodo(it)
            }
        }
    }

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    private val _title = mutableStateOf(TextFieldState(hint = "write your title.."))
    val title: State<TextFieldState> = _title

    private val _content = mutableStateOf(TextFieldState(hint = "write your task.."))
    val content: State<TextFieldState> = _content

    fun onEvent(event: TodoEvent) {
        viewModelScope.launch {
            when (event) {
                is TodoEvent.EnteredTitle -> {
                    _title.value = title.value.copy(
                        text = event.value
                    )
                }

                is TodoEvent.ChangeTitleFocus -> {
                    _title.value = title.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                title.value.text.isBlank()
                    )
                }

                is TodoEvent.EnteredContent -> {
                    _content.value = content.value.copy(
                        text = event.value
                    )
                }

                is TodoEvent.ChangeContentFocus -> {
                    _content.value = content.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                content.value.text.isBlank()
                    )
                }

                is TodoEvent.AddTodo -> {
                    if (title.value.text != "" && content.value.text != "") {
                        addTodo(
                            TodoModel(
                                if (savedStateHandle.get<Int>("todoId") != -1) savedStateHandle.get<Int>(
                                    "todoId"
                                )
                                else null,
                                title = title.value.text,
                                content = content.value.text
                            )
                        )
                        _event.emit(UiEvent.SaveNote)
                    }
                    else {
                        _event.emit(UiEvent.ShowSnackBar("write title & content first!"))
                    }
                }
            }
        }
    }

    fun getTodo(id: Int) {
        viewModelScope.launch {
            Log.e("dpsvns" , Thread.currentThread().name)
            val todoModel = repository.getTodo(id)
            _title.value = _title.value.copy(
                text = todoModel?.title.toString(),
                isHintVisible = false
            )
            _content.value = _content.value.copy(
                text = todoModel?.content.toString(),
                isHintVisible = false
            )
        }
    }

    private fun addTodo(damModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTodo(damModel)
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}