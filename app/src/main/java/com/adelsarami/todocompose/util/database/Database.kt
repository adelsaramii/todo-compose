package com.adelsarami.todocompose.util.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adelsarami.todocompose.core.todo.data.local.TodoDao
import com.adelsarami.todocompose.core.todo.data.model.TodoModel

@Database(
    entities = [TodoModel::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val todoDao: TodoDao

    companion object {
        val databaseName = "TodoDatabase"
    }

}