package com.example.to_doapp

import android.app.Application
import androidx.room.Room
import com.example.to_doapp.db.TodoDataBase

class MainApplication:Application() {

    companion object{
        lateinit var todoDatabase: TodoDataBase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase =  Room.databaseBuilder(
            applicationContext,
            TodoDataBase::class.java,
            TodoDataBase.NAME
        ).build()
    }
}