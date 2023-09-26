package com.adelsarami.todocompose.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.adelsarami.todocompose.core.todo.data.local.TodoDao
import com.adelsarami.todocompose.util.database.Database
import com.adelsarami.todocompose.util.database.Database.Companion.databaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            databaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(database: Database): TodoDao {
        return database.todoDao
    }

}