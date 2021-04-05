package com.diego.bizarelo.taskmanagement.ui.fragmets.listTasks

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.adapter.TaskRecyclerAdapter
import com.diego.bizarelo.taskmanagement.databinding.FragmentTaskListBinding
import com.diego.bizarelo.taskmanagement.model.Task


class TaskListFragment : Fragment(), TaskRecyclerAdapter.OnTaskListener {

    private var binding: FragmentTaskListBinding? = null
    private lateinit var recyclerViewListTask: RecyclerView
    private val taskListViewModel : TaskListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentBinding = FragmentTaskListBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        recyclerViewListTask = fragmentBinding.recyclerViewTask
        taskListViewModel.tasks.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                val adapter = TaskRecyclerAdapter(it, this)

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
                                    val alertDialog: AlertDialog? = activity?.let {
                                        val builder = AlertDialog.Builder(it)
                                        builder.apply {
                                            setTitle(R.string.deleteMsg)
                                            setPositiveButton(R.string.ok)
                                            { _, _ ->
                                                taskListViewModel.remove(position)
                                                adapter.notifyItemRemoved(position)
                                            }
                                            setNegativeButton(R.string.cancel)
                                            { _, _ ->
                                                adapter.notifyItemChanged(position)
                                            }
                                            setCancelable(false)
                                        }
                                        builder.create()
                                    }
                                    alertDialog!!.show()

                                } else if (direction == ItemTouchHelper.RIGHT) {
                                    if (!taskListViewModel.taskDone(position))
                                        Toast.makeText(
                                                context,
                                                R.string.taskAlreadyDone,
                                                Toast.LENGTH_SHORT
                                        ).show()
                                    else
                                        taskListViewModel.taskDone(position)
                                    adapter.notifyItemChanged(position)
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
        val bundle = bundleOf(
                R.string.position.toString() to position)
        findNavController().navigate(R.id.action_taskListFragment_to_taskEditFragment, bundle)
    }

    override fun callAlarmIntent(task: Task) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, task.title)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, task.time.toLong())
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }


}