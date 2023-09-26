package com.adelsarami.todocompose.core.todo.presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.adelsarami.todocompose.core.todo.presentation.note.component.BaseTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    navController: NavController,
    viewModel: TodoViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val title = viewModel.title.value
    val content = viewModel.content.value

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is TodoViewModel.UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is TodoViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        Modifier
            .fillMaxSize()
            .background(Color.White),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        content = { padding ->
            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable {
                                    navController.navigateUp()
                                }
                                .background(
                                    color = Color.Transparent
                                )
                                .padding(8.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (viewModel.savedStateHandle.get<Int>("todoId") == -1) {
                                "New Todo"
                            }
                            else {
                                "Edit Todo"
                            },
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    BaseTextField(
                        modifier = Modifier.padding(start = 16.dp, top = 36.dp, end = 16.dp),
                        text = title.text,
                        hint = title.hint,
                        onValueChange = {
                            viewModel.onEvent(TodoEvent.EnteredTitle(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(TodoEvent.ChangeTitleFocus(it))
                        },
                        isHintVisible = title.isHintVisible,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    BaseTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp),
                        text = content.text,
                        hint = content.hint,
                        onValueChange = {
                            viewModel.onEvent(TodoEvent.EnteredContent(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(TodoEvent.ChangeContentFocus(it))
                        },
                        isHintVisible = content.isHintVisible,
                        minHeight = 150.dp,
                        textStyle = MaterialTheme.typography.bodyMedium
                    )

                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        viewModel.onEvent(TodoEvent.AddTodo)
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                        text = "Save Todo", color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        }
    )

}