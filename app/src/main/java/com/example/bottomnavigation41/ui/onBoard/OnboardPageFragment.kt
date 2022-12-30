package com.example.bottomnavigation41.ui.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.FragmentOnboardPageBinding
import com.example.bottomnavigation41.utils.Preferences


class OnboardPageFragment(
    private var listenerSkip: () -> Unit,
    private var listenerNext: () -> Unit
) : Fragment() {
    private lateinit var binding: FragmentOnboardPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardPageBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListener()
    }


    private fun initViews() {
        arguments?.let {
            val data = it.getSerializable("onboard") as BoardModel
            binding.tvTitleBoard.text = data.title
            binding.tvDescriptionBoard.text = data.description
            data.img?.let { it1 -> binding.imgBoard.setImageResource(it1) }
            binding.btnSkipBoard.isVisible = data.isLast == false
            binding.btnNextBoard.isVisible = data.isLast == false
            binding.btnStartBoard.isVisible = data.isLast == true
        }
    }

    private fun initListener() {

        binding.btnNextBoard.setOnClickListener {
            listenerNext.invoke()
        }
        binding.btnSkipBoard.setOnClickListener {
            listenerSkip.invoke()
        }
        binding.btnStartBoard.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
            Preferences(requireContext()).setBoardingShowed(true)
        }
    }
}