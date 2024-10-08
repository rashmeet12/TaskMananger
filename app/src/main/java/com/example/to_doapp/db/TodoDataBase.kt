package com.example.to_doapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.to_doapp.Converter
import com.example.to_doapp.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converter::class)
abstract class TodoDataBase: RoomDatabase() {

    companion object {
        const val NAME ="Todo_DB"
    }
    abstract fun getTodoDao(): ToDoDao


}