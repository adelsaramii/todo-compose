package com.adelsarami.todocompose.core

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adelsarami.todocompose.core.todo.presentation.note.TodoScreen
import com.adelsarami.todocompose.core.todo.presentation.noteList.TodoListScreen
import com.adelsarami.todocompose.util.ui.Screen
import com.adelsarami.todocompose.util.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoComposeTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoListScreen.route
                    ) {
                        composable(route = Screen.TodoListScreen.route) {
                            TodoListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.TodoScreen.route + "/{todoId}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            TodoScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

}