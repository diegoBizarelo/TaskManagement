package com.diego.bizarelo.taskmanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.databinding.RecyclerItemLayoutBinding
import com.diego.bizarelo.taskmanagement.model.Task

class TaskRecyclerAdapter(
        private val tasks: List<Task>,
        private val taskListener: OnTaskListener)
        : RecyclerView.Adapter<TaskRecyclerAdapter.CardTaskViewHolder>() {

    class CardTaskViewHolder(binding: RecyclerItemLayoutBinding, taskListener: OnTaskListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val textViewRecyclerTaskTitle : TextView = binding.taskItemTitle
        val textViewRecyclerTaskImageView : ImageView = binding.taskItemImage
        val textViewRecyclerTaskDate : TextView = binding.taskItemDate
        private var taskListener : OnTaskListener? = null

        init {
            this.taskListener = taskListener
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            taskListener?.onTaskClick(adapterPosition)
        }
//        val textViewRecyclerTaskDate : TextView = view.findViewById(R.id.task_item_date)

    }

    interface OnTaskListener {
        fun onTaskClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = RecyclerItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CardTaskViewHolder(biding, taskListener)
    }

    override fun onBindViewHolder(holder: CardTaskViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.textViewRecyclerTaskTitle.text = task.title
        holder.textViewRecyclerTaskDate.text = task.date
        if (!task.done) {
            holder.textViewRecyclerTaskImageView.setImageResource(R.drawable.ic_pending_foreground)
        } else {
            holder.textViewRecyclerTaskImageView.setImageResource(R.drawable.ic_task_done_location_foreground)
        }

    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}