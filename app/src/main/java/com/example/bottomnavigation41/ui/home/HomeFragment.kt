package com.example.bottomnavigation41.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnavigation41.App
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initViews()
        setData()
        initListeners()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onClick, this::onLongClick)
    }

    private fun onClick(pos: Int) {
        val task= taskAdapter.getTask(pos)
        findNavController().navigate(R.id.newTaskFragment, bundleOf(EDIT_KEY to task))
    }

    private fun onLongClick(pos: Int) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить?")
            setPositiveButton("yes") { _, _ ->
                App.db.Dao().deleteTask(taskAdapter.getTask(pos))
                setData()
            }
            setNegativeButton("no") { dialog, _ ->
                dialog.dismiss()

            }
            show()
        }
    }

    //сортировака по дате и по алфавиту
    @Deprecated("Deprecated in Java")
    override fun
            onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort) {
            val items = arrayOf("date", "a-z")
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setTitle(" sort by")
                setItems(items) { _, which ->
                    when (which) {
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

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initListeners() {
        binding.btnAddFab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }
    }


    private fun initViews() {

        binding.rvFab.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
    }

    private fun setData() {
        val listOfTask = App.db.Dao().getAllTasks()
        taskAdapter.addTasks(listOfTask)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val EDIT_KEY: String = "edit"
    }
}