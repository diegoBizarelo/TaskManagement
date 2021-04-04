package com.diego.bizarelo.taskmanagement.ui.fragmets.listTasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.bizarelo.taskmanagement.database.Repository
import com.diego.bizarelo.taskmanagement.model.Task

class TaskListViewModel : ViewModel() {
    private val _tasks = MutableLiveData(Repository.getAll())
    val tasks: LiveData<List<Task>> = _tasks as LiveData<List<Task>>

    fun addTask(task: Task) {
        _tasks.value!!.add(task)
    }

    fun remove(position: Int) {
        _tasks.value?.removeAt(position)
        Repository.delete(position)
    }

    fun editTask(position: Int, title: String, description: Long) {
        val task = _tasks.value!![position]
        task.title = title
    }
}