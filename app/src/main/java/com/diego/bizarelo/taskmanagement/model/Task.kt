package com.diego.bizarelo.taskmanagement.model

import java.text.SimpleDateFormat
import java.util.*

class Task(
        var title: String,
        var time: String,
        var done: Boolean = false,) {

    val date : String
    get() {
        val date = Date(time.toLong())
        val format = SimpleDateFormat("dd MMM YYYY", Locale("pt", "BR"))
        return format.format(date).toString()
    }
}