package com.adelsarami.todocompose.core.todo.presentation.note

import androidx.compose.ui.focus.FocusState
import com.adelsarami.todocompose.core.todo.data.model.TodoModel

sealed class TodoEvent {
    data class EnteredTitle(val value: String): TodoEvent()
    data class ChangeTitleFocus(val focusState: FocusState): TodoEvent()
    data class EnteredContent(val value: String): TodoEvent()
    data class ChangeContentFocus(val focusState: FocusState): TodoEvent()

    object AddTodo : TodoEvent()
}
