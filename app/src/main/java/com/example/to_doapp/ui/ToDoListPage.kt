package com.example.to_doapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_doapp.R
import com.example.to_doapp.ToDoViewModel
import com.example.to_doapp.Todo
import com.example.to_doapp.ui.theme.ToDoColor
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun TodolistPage(viewModel: ToDoViewModel){
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        AppHeader()
        Spacer(modifier = Modifier.height(8.dp))


        Column(
            modifier = Modifier
               // .fillMaxHeight()
                .padding(4.dp)
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                OutlinedTextField(value = inputText, onValueChange ={inputText=it}
                    , label = { Text(text = "Enter Task")},modifier = Modifier.clip(
                    RoundedCornerShape(3.dp)
                ).padding(8.dp).width(240.dp)
                )
                Button(onClick = {
                    viewModel.addTodo(inputText)
                    inputText=""
                },
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .align(Alignment.CenterVertically)
                        ) {
                    Text(text = "Add",fontFamily = androidx.compose.ui.text.font.FontFamily.Serif)
                }
            }
            todoList?.let {
                LazyColumn(content = {
                    itemsIndexed(it) { index, item ->
                        TodoItem(item = item, onDelete = { viewModel.deleteTodo(item.id) })
                    }
                })
            }?: Text(modifier = Modifier.fillMaxWidth(),text = "No Tasks",
                textAlign = TextAlign.Center,
                fontSize = 24.sp)




        }
    }

}

@Composable
fun AppHeader(){
    Column {
        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                // .background(color = )
                .shadow(
                    elevation = 2.dp,  // The higher the value, the more pronounced the shadow
                    shape = RoundedCornerShape(
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ), // Adjust to match the shape
                    clip = false
                )
        )
        {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center), text = "TODO",
                color = Color.DarkGray,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Serif, fontSize = 26.sp
            )
        }
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit = {}){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(22.dp))
        .background(color = ToDoColor)
        .padding(12.dp)
        .shadow(elevation = 70.dp,  // The higher the value, the more pronounced the shadow
            clip = true)
        , verticalAlignment = Alignment.CenterVertically){
        Column(modifier = Modifier.weight(1f)) {
            Text(text = SimpleDateFormat("HH:mm aa   |   dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt),
                fontSize = 11.sp, color = Color.LightGray)
            Text(text = item.title,
                fontSize = 21.sp, color = Color.White)
        }
        IconButton(onClick = {  onDelete() },) {
            Icon(modifier = Modifier.size(30.dp),painter=painterResource(id = R.drawable.baseline_delete_outline_24)
                ,contentDescription ="delete",
                tint = Color.Red,
                )
        }
    }

}

