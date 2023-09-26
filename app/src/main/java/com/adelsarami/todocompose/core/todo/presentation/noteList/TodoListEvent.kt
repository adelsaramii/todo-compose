package com.adelsarami.todocompose.core.todo.presentation.noteList

import com.adelsarami.todocompose.core.todo.data.model.TodoModel

sealed class TodoListEvent {
    data class DeleteTodo(val todoModel: TodoModel) : TodoListEvent()
    object AddTodo : TodoListEvent()
}
