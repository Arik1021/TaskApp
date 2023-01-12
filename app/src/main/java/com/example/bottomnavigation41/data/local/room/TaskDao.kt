package com.example.bottomnavigation41.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bottomnavigation41.ui.home.TaskModel
import com.example.bottomnavigation41.ui.profile.ProfileModel

@Dao
interface TaskDao {
    @Insert
    fun insert(task: TaskModel)
    @Query("SELECT * FROM TaskModel ")
    fun getAllTasks(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY id DESC ")
    fun getListByDate(): List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY title ASC  ")
    fun getA_Z(): List<TaskModel>

//ORDER BY title DESC

    @Delete
    fun deleteTask(task: TaskModel )

    @Update
    fun updateTask(taskModel: TaskModel)

}

//Dao это интерфейс,в котором описываем, что делать
