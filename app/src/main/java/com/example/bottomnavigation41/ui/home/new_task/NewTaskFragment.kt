package com.example.bottomnavigation41.ui.home.new_task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.FragmentNewTaskBinding
import kotlin.math.log


class NewTaskFragment : Fragment() {


    private lateinit var binding: FragmentNewTaskBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context), container, false)

        initViews()
        initListeners()

        return binding.root

    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            setFragmentResult(
                "new_task", bundleOf(
                    "title" to binding.etTitle.text.toString(),
                    "desc" to binding.desc.text.toString()
                )
            )
            findNavController().navigateUp()
        }
    }

    private fun initViews() {

    }
}
