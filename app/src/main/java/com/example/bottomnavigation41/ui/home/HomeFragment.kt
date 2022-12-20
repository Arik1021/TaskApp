package com.example.bottomnavigation41.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigation41.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter()
    }


    private fun initListeners() {
        binding.btnAddFab.setOnClickListener {
            findNavController().navigate(com.example.bottomnavigation41.R.id.newTaskFragment)
        }
    }

    private fun initViews() {
        binding.rvFab.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }

        setFragmentResultListener("new_task") { key, bundle ->
            val title = bundle.get("title")
            val description = bundle.get("desc")
            taskAdapter.addTask(TaskModel(title.toString(),description.toString()) )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}