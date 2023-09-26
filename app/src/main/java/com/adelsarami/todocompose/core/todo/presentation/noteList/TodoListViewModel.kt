package com.adelsarami.todocompose.core.todo.presentation.noteList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.adelsarami.todocompose.core.todo.data.TodoRepository
import com.adelsarami.todocompose.core.todo.data.model.TodoModel
import com.adelsarami.todocompose.util.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(private val repository: TodoRepository) :
    BaseViewModel() {

    var getTodoListVisibility = mutableStateOf(false)

    private fun getTodoListVisibility() {
        viewModelScope.launch {
            getTodoListVisibility.value = true
        }
    }

    init {
        getTodoList()
    }

    private var _getTodoList = MutableStateFlow<List<TodoModel?>?>(arrayListOf())
    var getTodoList = _getTodoList.asStateFlow()

    private fun getTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            getTodoListVisibility()
            getFlow(_getTodoList) { repository.getTodoList() }
        }
    }

    fun deleteTodo(damModel: TodoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(damModel)
        }
    }

}