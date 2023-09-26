package com.adelsarami.todocompose.core.todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adelsarami.todocompose.core.todo.data.model.TodoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoModel")
    fun getTodoList(): Flow<List<TodoModel?>?>

    @Query("SELECT * FROM TodoModel WHERE id = :id")
    suspend fun getTodo(id: Int): TodoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTodo(todoModel: List<TodoModel?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoModel: TodoModel)

    @Delete
    suspend fun deleteTodo(note: TodoModel)

}