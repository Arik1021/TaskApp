package com.example.bottomnavigation41.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation41.App
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.TaskItemBinding
import com.example.bottomnavigation41.loadImage

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {


    private var taskList = arrayListOf<TaskModel>()
    fun addTask(taskModel: TaskModel){
        taskList.add(0,taskModel)
       notifyItemChanged(0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
        if(position % 2 == 0)
        {
            //holder.rootView.setBackgroundColor(Color.BLACK);
            holder.itemView.setBackgroundResource(R.color.black);
        }
        else
        {
            //holder.rootView.setBackgroundColor(Color.WHITE);
            holder.itemView.setBackgroundResource(R.color.white);
        }
    }


    override fun getItemCount(): Int = taskList.size


    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(list:List<TaskModel>){
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()

    }
    inner class ViewHolder(private var binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

//удаление дз4
        @SuppressLint("NotifyDataSetChanged")
        fun bind(taskModel: TaskModel) {
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
//            binding.ivItemTask.setImageURI(taskModel.imgUri.toUri())
            binding.ivItemTask.loadImage(taskModel.imgUri)

            itemView.setOnLongClickListener {
                val bilder = AlertDialog.Builder(itemView.context)
                with(bilder){
                    setTitle("ы точно хоти ${taskModel.title}")
                    setPositiveButton("yes"){
                        dialog,which ->
                        App.db.Dao().deleteTask(taskModel)
                        taskList.clear()
                        taskList.addAll(App.db.Dao().getAllTasks())
                        notifyDataSetChanged()
                    }
                    setNegativeButton("no"){dialog,which ->
                        dialog.dismiss()

                    }
                    show()
                }
                return@setOnLongClickListener true

            }
        }

    }

}