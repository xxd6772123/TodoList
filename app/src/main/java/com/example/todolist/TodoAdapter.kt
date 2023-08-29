package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return todos.size;
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
//            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
//            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        Log.e("test", "onBindViewHolder1: " + curTodo.title)
        var tvTitle = holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        tvTitle.text = curTodo.title
        Log.e("test", "onBindViewHolder2: " +  tvTitle.text)
        var cdDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        cdDone.isChecked = curTodo.isChecked
        toggleStrikeThrough(tvTitle, curTodo.isChecked)
        cdDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(tvTitle, isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }

    }
}