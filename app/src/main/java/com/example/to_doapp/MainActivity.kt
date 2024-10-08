package com.example.to_doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.to_doapp.ui.TodolistPage
import com.example.to_doapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val todoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        setContent {
            ToDoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                   TodolistPage(todoViewModel)
                }
            }
        }
    }
}

