package com.example.bottomnavigation41.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigation41.App
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskModel: TaskModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initViews()
        initListeners()

        return binding.root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter()
    }

    //сортировака по дате и по алфавиту
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort){
            val items = arrayOf("date", "a-z")
            val bilder = AlertDialog.Builder(requireContext())
            with(bilder){
                setTitle(" sort by")
                setItems(items){dialog,which ->
                    when(which){
                        0 -> {
                            taskAdapter.addTasks(App.db.Dao().getListByDate())
                        }
                        1 -> {
                            taskAdapter.addTasks(App.db.Dao().getA_Z())
                        }
                    }
                }
                show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
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


        val listOfTask = App.db.Dao().getAllTasks()

        taskAdapter.addTasks(listOfTask.reversed())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}