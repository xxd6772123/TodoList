package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private lateinit var bing:ItemTodoBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        bing = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(
            bing.root
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
        bing.tvTodoTitle.text = curTodo.title
        var cdDone = holder.itemView.findViewById<CheckBox>(R.id.cbDone)
        cdDone.isChecked = curTodo.isChecked
        toggleStrikeThrough(bing.tvTodoTitle, curTodo.isChecked)
        cdDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(bing.tvTodoTitle, isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }

    }
}