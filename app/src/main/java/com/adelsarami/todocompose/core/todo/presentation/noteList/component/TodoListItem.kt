package com.adelsarami.todocompose.core.todo.presentation.noteList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adelsarami.todocompose.core.todo.data.model.TodoModel
import com.adelsarami.todocompose.util.ui.Screen
import com.adelsarami.todocompose.util.ui.theme.LightGrey

@Composable
fun TodoListItem(
    modifier: Modifier,
    todoModel: TodoModel?,
    navController: NavController,
    delete: () -> Unit
) {

    Column(
        modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(Screen.TodoScreen.route + "/${todoModel?.id}")
            }
            .background(LightGrey, shape = RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = todoModel?.title.toString(),
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(end = 36.dp)
                    .align(Alignment.CenterStart)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        delete.invoke()
                    }
                    .background(
                        color = Color.Transparent
                    )
                    .padding(8.dp)
                    .align(Alignment.CenterEnd),
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.DarkGray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = todoModel?.content.toString(),
            color = Color.DarkGray,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}