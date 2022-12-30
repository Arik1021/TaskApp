package com.example.bottomnavigation41.data.local.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnavigation41.ui.home.TaskModel

@Database(entities = [TaskModel::class],version =1 )
abstract class TaskBase: RoomDatabase() {
    abstract fun Dao(): TaskDao


}
//Data Base это мотор, который будет сохранаять наш ресайкл, т.е он будет создавать и сохранять