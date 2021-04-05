package com.diego.bizarelo.taskmanagement.ui.fragmets.editTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.bizarelo.taskmanagement.database.Repository
import com.diego.bizarelo.taskmanagement.model.Task

class TaskViewModel(private val taskPosition: Int) : ViewModel() {
    private val _task = MutableLiveData<Task>()

    private val _tempTask = MutableLiveData<Task>()
    val task
        get() = _tempTask

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    init {
        _status.value = false
        _task.value = Repository.get(taskPosition)
        val task = Task(_task.value!!.title, _task.value!!.time, _task.value!!.done)
        _tempTask.value = task
    }

    fun setStatus(status: Boolean) {
        _tempTask.value!!.done = status
    }

    fun setTitle(title: String) {
        _tempTask.value!!.title = title
    }

    fun setTime(time: String) {
        _tempTask.value!!.time = time
        _status.value = true
    }

    fun save() {
        Repository.update(taskPosition, _tempTask.value!!)
    }
}