package com.diego.bizarelo.taskmanagement.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.bizarelo.taskmanagement.database.Repository
import com.diego.bizarelo.taskmanagement.model.Task

class TaskListViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    init {
        _tasks.value = Repository.getAll()
    }
}