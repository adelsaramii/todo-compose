package com.adelsarami.todocompose.core.todo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
)