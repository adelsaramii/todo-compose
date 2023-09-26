package com.adelsarami.todocompose.core.todo.presentation.noteList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adelsarami.todocompose.core.todo.presentation.noteList.component.TodoListItem
import com.adelsarami.todocompose.util.ui.Screen
import com.adelsarami.todocompose.util.ui.component.NeverOverScroll

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel: TodoListViewModel = hiltViewModel()
) {

    val visibility = viewModel.getTodoListVisibility.value
    val todoList = viewModel.getTodoList.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.TopCenter)
            ) {
                Row(
                    Modifier.padding(top = 24.dp, start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "welcome to ",
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 22.sp
                    )
                    Text(
                        text = "todo",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorScheme.primary,
                        fontSize = 22.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                NeverOverScroll {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(todoList.value ?: listOf(),
                            key = {
                                it?.id ?: 0
                            }) { todoModel ->
                            TodoListItem(
                                Modifier.animateItemPlacement(
                                    animationSpec = tween(
                                        durationMillis = 500,
                                        easing = LinearOutSlowInEasing,
                                    )
                                ), todoModel, navController
                            ) {
                                todoModel?.let { viewModel.deleteTodo(it) }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.TodoScreen.route + "/-1")
            },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add todo")
        }
    }

}