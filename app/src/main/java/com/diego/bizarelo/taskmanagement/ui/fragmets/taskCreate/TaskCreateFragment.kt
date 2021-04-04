package com.diego.bizarelo.taskmanagement.ui.fragmets.taskCreate

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.diego.bizarelo.taskmanagement.R
import com.diego.bizarelo.taskmanagement.databinding.FragmentTaskCreateBinding
import java.util.*

class TaskCreateFragment : Fragment() {

    private var binding: FragmentTaskCreateBinding? = null
    private val taskViewModel : TaskCreateViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentTaskCreateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        taskViewModel.status.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                binding!!.createTaskDate.text = taskViewModel.task.value?.date
            }
        })
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = taskViewModel
            createFragment = this@TaskCreateFragment
        }
    }

    fun showCalendar() {
        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener {
            _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                taskViewModel.setTime(selectedDate.timeInMillis.toString())
                },
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    fun saveTask() {
        try {
            val title = binding!!.createTaskTitle
            if (title.text.toString().isEmpty()) {
                title.error = "campo obrigatório"
                return
            }
            taskViewModel.setTitle(title.text.toString())
            taskViewModel.save()
            findNavController().navigate(R.id.action_taskDetailsFragment_to_taskListFragment)
        } catch(e: Exception) {
            Toast.makeText(
                    context,
                    "Ocorreu algum erro, por favor reinicie o app!",
                    Toast.LENGTH_SHORT).show()
        }
    }

}