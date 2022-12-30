package com.example.bottomnavigation41

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnavigation41.data.local.room.TaskBase
import com.example.bottomnavigation41.data.local.room.TaskDao

class App: Application() {
    override fun onCreate() {
        super.onCreate()
//и переменную мы всегда инициализируем
        db = Room.databaseBuilder(this,//context
            TaskBase::class.java,//class dataBase
            "database")//name
            .allowMainThreadQueries()  //даю доступ самому главному
            .build()//строим    

    }
    companion object{
        //создали перменную
        lateinit var db :TaskBase

    }

}