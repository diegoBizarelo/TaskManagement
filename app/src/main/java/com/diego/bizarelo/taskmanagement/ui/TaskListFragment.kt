package com.diego.bizarelo.taskmanagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.adapter.TaskRecyclerAdapter


class TaskListFragment : Fragment() {

    private lateinit var recyclerViewListTask: RecyclerView
    private val taskListViewModel : TaskListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        recyclerViewListTask = view.findViewById(R.id.recycler_view_task)
        taskListViewModel.tasks.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                recyclerViewListTask.adapter = TaskRecyclerAdapter(it)
            }
        })
        return view
    }


}