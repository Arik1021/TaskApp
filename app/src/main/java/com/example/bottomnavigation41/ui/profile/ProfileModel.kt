package com.example.bottomnavigation41.ui.profile

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileModel(
    @PrimaryKey(autoGenerate = true)
    var name: String?=null,
    var email: String?=null,
    var number_phone: String?=null,
    var gender: String?=null,
    var birthday: String?=null,


):java.io.Serializable

