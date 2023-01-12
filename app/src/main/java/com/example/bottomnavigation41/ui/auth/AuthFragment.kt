package com.example.bottomnavigation41.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnavigation41.databinding.FragmentAuthBinding



class AuthFragment : Fragment() {

lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(inflater, container, false)


        initViews()
        initListener()
        return binding.root
    }

    private fun initListener() {

    }

    private fun initViews() {

    }


}