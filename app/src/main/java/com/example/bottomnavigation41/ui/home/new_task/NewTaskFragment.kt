package com.example.bottomnavigation41.ui.home.new_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation41.App
import com.example.bottomnavigation41.databinding.FragmentNewTaskBinding
import com.example.bottomnavigation41.ui.home.TaskModel


class NewTaskFragment : Fragment() {


    private lateinit var binding: FragmentNewTaskBinding

    private var imgUri: String = ""


    private val mGetContent: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            binding.ivNewTask.setImageURI(uri)
            imgUri = uri.toString()


        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context), container, false)

        initViews()
        initListeners()

        return binding.root

    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            App.db.Dao().insert(//запиши модельку, где картинка ткая, тайтл такой и описание такое.
                TaskModel(
                    imgUri = imgUri,
                    title = binding.etTitle.text.toString(),
                    description = binding.desc.text.toString()
                )
            )

            findNavController().navigateUp()
        }
        binding.ivNewTask.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }

    private fun initViews() {

    }
}
