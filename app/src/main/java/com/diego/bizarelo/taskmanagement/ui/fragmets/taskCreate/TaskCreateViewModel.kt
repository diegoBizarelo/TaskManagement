package com.diego.bizarelo.taskmanagement.ui.fragmets.taskCreate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.bizarelo.taskmanagement.database.Repository
import com.diego.bizarelo.taskmanagement.model.Task
import java.util.*

class TaskCreateViewModel : ViewModel() {
    private val _task = MutableLiveData<Task>()
    val task
        get() = _task

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    init {
        val now = Calendar.getInstance()
        _task.value = Task("", now.timeInMillis.toString(), false)
    }

    fun setTitle(title: String) {
        _task.value?.title = title
    }

    fun setTime(time: String) {
        _task.value?.time = time
        _status.value = true
    }

    fun save(): Boolean {
        if (!_task.value?.title.isNullOrBlank()) {
            val task = Task(_task.value!!.title, _task.value!!.time, false)
            Repository.add(task)
            return true
        }
        return false
    }

}