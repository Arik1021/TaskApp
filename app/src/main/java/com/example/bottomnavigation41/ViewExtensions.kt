package com.example.bottomnavigation41

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.loadImage(url: String){

    Glide.with(this).load(url).centerCrop().circleCrop().into(this as ImageView)

}