package com.diego.bizarelo.taskmanagement.ui.fragmets.listTasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.adapter.TaskRecyclerAdapter
import com.diego.bizarelo.taskmanagement.databinding.FragmentTaskListBinding


class TaskListFragment : Fragment(), TaskRecyclerAdapter.OnTaskListener {

    private var binding: FragmentTaskListBinding? = null
    private lateinit var recyclerViewListTask: RecyclerView
    private val taskListViewModel : TaskListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentTaskListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        recyclerViewListTask = fragmentBinding.recyclerViewTask
        taskListViewModel.tasks.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                val adapter = TaskRecyclerAdapter(it, this) //{
//                        val taskViewModel : TaskViewModel by navGraphViewModels(R.id.task_selection_navigation) {
//                                TaskViewModelFactory(it.)
//                        }
//                        findNavController().navigate(R.id.action_taskListFragment_to_taskEditFragment)
//                }

                recyclerViewListTask.adapter = adapter
                val helper = ItemTouchHelper(
                        object: ItemTouchHelper.SimpleCallback(
                            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                        {
                        override fun onMove(recyclerView: RecyclerView,
                                            viewHolder: RecyclerView.ViewHolder,
                                            target: RecyclerView.ViewHolder): Boolean  = false
                        override fun onSwiped(
                                viewHolder: RecyclerView.ViewHolder,
                                direction: Int) {
                            val position = viewHolder.adapterPosition
                                if (direction == ItemTouchHelper.LEFT) {
                                    taskListViewModel.remove(position)
                                    adapter.notifyItemRemoved(position)
                                } else if (direction == ItemTouchHelper.RIGHT) {

                                }
                            }

                }
                )

                helper.attachToRecyclerView(recyclerViewListTask)
            }
        })



        return fragmentBinding.root
    }

    fun onClick() {
        Log.i("Clicked","cliquei no botão")
        findNavController().navigate(R.id.action_taskListFragment_to_taskDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = taskListViewModel
            lifecycleOwner = viewLifecycleOwner
            taskFragment = this@TaskListFragment
        }
    }

    override fun onTaskClick(position: Int) {
        val task = taskListViewModel.tasks.value?.get(position)
        val bundle = bundleOf(
                "taskPosition" to position
        )
        findNavController().navigate(R.id.action_taskListFragment_to_taskEditFragment, bundle)
    }


}