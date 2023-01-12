package com.example.bottomnavigation41.ui.home.new_task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation41.App
import com.example.bottomnavigation41.databinding.FragmentNewTaskBinding
import com.example.bottomnavigation41.ui.home.HomeFragment.Companion.EDIT_KEY
import com.example.bottomnavigation41.ui.home.TaskModel


class NewTaskFragment : Fragment() {


    private lateinit var binding: FragmentNewTaskBinding
    private lateinit var task: TaskModel
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

    private fun saveData() {
        App.db.Dao().insert(//запиши модельку, где картинка ткая, тайтл такой и описание такое.
            TaskModel(
                imgUri = imgUri,
                title = binding.etTitle.text.toString(),
                description = binding.etDesc.text.toString()
            )
        )
    }

    private fun updateData(taskModel: TaskModel) {
        taskModel.title = binding.etTitle.text.toString()
        taskModel.description = binding.etDesc.text.toString()
        App.db.Dao().updateTask(taskModel)// и здесь ее обновиить
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            if (arguments != null) {
               updateData(task)
            } else {
                saveData()
            }
            findNavController().navigateUp()
        }
        binding.ivNewTask.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        if (arguments != null) {
            binding.btnSave.text = " update"
            task = arguments?.getSerializable(EDIT_KEY) as TaskModel
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.description)
            binding.ivNewTask.setImageURI(task.imgUri.toUri())
        } else {
            binding.btnSave.text = " Save"
        }

// каритинку надо установать

    }
}
