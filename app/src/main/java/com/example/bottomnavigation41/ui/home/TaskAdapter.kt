package com.example.bottomnavigation41.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.TaskItemBinding
import com.example.bottomnavigation41.loadImage

class TaskAdapter(
    private var onClick: (Int) -> Unit,
    private var onLongClick: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {


    private var taskList = arrayListOf<TaskModel>()


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
        if (position % 2 == 0) {
            //holder.rootView.setBackgroundColor(Color.BLACK);
            holder.itemView.setBackgroundResource(R.color.black)
        } else {
            //holder.rootView.setBackgroundColor(Color.WHITE);
            holder.itemView.setBackgroundResource(R.color.white)
        }
    }


    override fun getItemCount(): Int = taskList.size


    @SuppressLint("NotifyDataSetChanged")
    fun addTasks(list: List<TaskModel>) {
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()

    }

    fun getTask(pos: Int): TaskModel {
        return taskList[pos]
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

                onLongClick(adapterPosition)
                Toast.makeText(itemView.context, adapterPosition.toString(), Toast.LENGTH_SHORT)
                    .show()

                return@setOnLongClickListener true

            }
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }

    }

}