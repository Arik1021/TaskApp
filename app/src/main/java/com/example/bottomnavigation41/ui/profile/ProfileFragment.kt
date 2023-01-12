package com.example.bottomnavigation41.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.bottomnavigation41.databinding.FragmentProfileBinding
import com.example.bottomnavigation41.loadImage
import com.example.bottomnavigation41.utils.Preferences


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    lateinit var binding: FragmentProfileBinding
    private lateinit var preference: Preferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    private val mGetContent: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.GetContent()// вместо байнд использую расширение глайда и сохраняю картинку
        ) { uri ->
            binding.ivProfile.loadImage(uri.toString())

            Log.e("lol", "Ссылка на фото: $uri ")
            Preferences(requireContext()).saveImageUri(uri.toString())

        }


    private fun initListeners() {
        binding.ivProfile.setOnClickListener {
            mGetContent.launch("image/*")

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference = Preferences(requireContext())
        binding.ivProfile.loadImage(preference.getImageUri())//здесь картинка загружается на пользовательский экран

        initListeners()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}