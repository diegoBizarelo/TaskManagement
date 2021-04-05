package com.diego.bizarelo.taskmanagement.ui.fragmets.editTask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.databinding.FragmentTaskEditBinding
import java.util.*


class TaskEditFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskViewModelFactory: TaskViewModelFactory
    private var binding : FragmentTaskEditBinding? = null

    private var taskPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentBinding = FragmentTaskEditBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        taskPosition = arguments?.getInt(R.string.position.toString())
        if (taskPosition == null)
            findNavController().popBackStack()

        taskViewModelFactory = TaskViewModelFactory(taskPosition!!)
        taskViewModel =  ViewModelProvider(this, taskViewModelFactory).get(TaskViewModel::class.java)
        taskViewModel.status.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                binding!!.editTaskDate.text = taskViewModel.task.value?.date
            }
        })

        if (taskViewModel.task.value!!.done) {
            binding!!.taskStatusButton.isChecked = true
        }

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            task = taskViewModel
            editFragment = this@TaskEditFragment
        }
    }

    fun showCalendar() {
        val now = Calendar.getInstance()
        val selectedDate = Calendar.getInstance()

        val timePicker = TimePickerDialog(activity, { _, hour, minute ->
            selectedDate.set(Calendar.HOUR, hour)
            selectedDate.set(Calendar.MINUTE, minute)
            taskViewModel.setTime(selectedDate.timeInMillis.toString())
        }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false)

        val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener {
            _, year, month, dayOfMonth ->
            selectedDate.set(Calendar.YEAR,year)
            selectedDate.set(Calendar.MONTH,month)
            selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            timePicker.show()
        },
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    fun toggleStatus() {
        taskViewModel.setStatus(binding!!.taskStatusButton.isChecked)
    }

    fun saveTask() {
        try {
            val title = binding!!.editTaskTitle
            if (title.text.toString().isEmpty()) {
                title.error = R.string.important.toString()
                return
            }
            taskViewModel.setTitle(title.text.toString())
            taskViewModel.save()
            findNavController().navigate(R.id.action_taskEditFragment_to_taskListFragment)
        } catch (e: Exception) {
            Toast.makeText(
                    context,
                    R.string.erroMsg,
                    Toast.LENGTH_SHORT).show()
        }
    }
}