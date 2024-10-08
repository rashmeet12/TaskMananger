package com.example.to_doapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.to_doapp.Todo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo")
    fun getAllTodo(): LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Query("DELETE FROM todo WHERE id = :id")
    fun deleteTodo(id: Int)

}