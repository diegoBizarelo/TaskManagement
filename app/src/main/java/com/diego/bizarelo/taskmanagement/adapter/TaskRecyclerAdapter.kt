package com.diego.bizarelo.taskmanagement.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
        val imageRecyclerTaskImageView : ImageView = binding.taskItemImage
        val textViewRecyclerTaskDate : TextView = binding.taskItemDate
        val alarmButtonRecyclerTask : ImageButton = binding.taskItemAddAlarm
        private var taskListener : OnTaskListener? = null

        init {
            this.taskListener = taskListener
            binding.root.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            taskListener?.onTaskClick(adapterPosition)
        }
    }

    interface OnTaskListener {
        fun onTaskClick(position: Int)
        fun callAlarmIntent(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = RecyclerItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CardTaskViewHolder(biding, taskListener)
    }

    override fun onBindViewHolder(holder: CardTaskViewHolder, position: Int) {
        val task: Task = tasks[position]
        holder.textViewRecyclerTaskTitle.apply {
            if (task.done)
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            text = task.title
        }
        holder.textViewRecyclerTaskDate.apply {
            if (task.done)
                paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            text = task.date
        }
        if (!task.done) {
            holder.imageRecyclerTaskImageView.setImageResource(R.drawable.ic_pending_foreground)
        } else {
            holder.imageRecyclerTaskImageView.setImageResource(R.drawable.ic_task_done_location_foreground)
            holder.alarmButtonRecyclerTask.visibility = View.INVISIBLE
        }
        holder.alarmButtonRecyclerTask.setOnClickListener {
            taskListener.callAlarmIntent(task)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}