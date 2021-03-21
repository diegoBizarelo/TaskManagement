package com.diego.bizarelo.taskmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.model.Task

class TaskRecyclerAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskRecyclerAdapter.CardTaskViewHolder>() {

    class CardTaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewRecyclerTaskTitle : TextView = view.findViewById(R.id.task_item_title)
        val textViewRecyclerTaskDate : TextView = view.findViewById(R.id.task_item_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_layout, parent, false)
        return CardTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardTaskViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.textViewRecyclerTaskTitle.text = task.title
        holder.textViewRecyclerTaskDate.text = task.description
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}