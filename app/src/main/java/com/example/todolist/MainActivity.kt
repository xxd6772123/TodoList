package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bing:ActivityMainBinding

    private lateinit var todoAdapter:TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bing = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bing.root)
        todoAdapter = TodoAdapter(mutableListOf())

        bing.rvTodoItems.adapter = todoAdapter
        bing.rvTodoItems.layoutManager = LinearLayoutManager(this)

        bing.btnAddTodo.setOnClickListener {
            if (bing.etTodoTitle.text.toString().isNotEmpty()){
                val todo = Todo( bing.etTodoTitle.text.toString())
                todoAdapter.addTodo(todo)
                bing.etTodoTitle.text.clear()
            }
        }
        bing.btnDeleteDons.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}