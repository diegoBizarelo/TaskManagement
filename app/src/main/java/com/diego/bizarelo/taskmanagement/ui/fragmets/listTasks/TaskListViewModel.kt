package com.diego.bizarelo.taskmanagement.ui.fragmets.listTasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.bizarelo.taskmanagement.database.Repository
import com.diego.bizarelo.taskmanagement.model.Task

class TaskListViewModel : ViewModel() {
    private val _tasks = MutableLiveData(Repository.getAll())
    val tasks: LiveData<List<Task>> = _tasks as LiveData<List<Task>>

    fun taskDone(position: Int) : Boolean {
        val task = _tasks.value!![position]
        if (task.done) {
            return false
        }
        val temTask = Task(task.title, task.time, true)
        Repository.update(position, temTask)
        return true
    }

    fun remove(position: Int) {
        Repository.delete(position)
    }
}