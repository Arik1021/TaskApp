package com.example.bottomnavigation41.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var imgUri: String,
    var title: String,
    var description: String
):java.io.Serializable


//Entity это сущность моделька, в нашем случае моделька, с которыми мы будем работать в датабазе
