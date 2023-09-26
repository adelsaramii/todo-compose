package com.adelsarami.todocompose.core.todo.data

import com.adelsarami.todocompose.core.todo.data.local.TodoDao
import com.adelsarami.todocompose.core.todo.data.model.TodoModel
import com.example.smartdam.util.base.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(private val dao: TodoDao) {

    fun getTodoList(): Flow<List<TodoModel?>?> {
        return dao.getTodoList()
    }

    suspend fun getTodo(id: Int): TodoModel? {
        return dao.getTodo(id)
    }

    suspend fun insertTodo(todoModel: TodoModel) {
        dao.insertTodo(todoModel)
    }

    suspend fun insertAllTodo(todoModel: List<TodoModel?>?) {
        dao.insertAllTodo(todoModel)
    }

    suspend fun deleteTodo(todoModel: TodoModel) {
        dao.deleteTodo(todoModel)
    }

}