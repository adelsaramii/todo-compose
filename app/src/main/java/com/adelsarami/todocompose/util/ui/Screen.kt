package com.adelsarami.todocompose.util.ui

sealed class Screen(val route: String) {
    object TodoListScreen: Screen("todo_list_screen")
    object TodoScreen: Screen("todo_screen")
}