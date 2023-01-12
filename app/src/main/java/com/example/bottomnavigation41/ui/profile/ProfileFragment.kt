package com.example.bottomnavigation41.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation41.R
import com.example.bottomnavigation41.databinding.FragmentProfileBinding
import com.example.bottomnavigation41.loadImage
import com.example.bottomnavigation41.utils.Preferences
import com.google.firebase.auth.FirebaseAuth


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
        binding.signOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.authFragment)


        }

    }

//    fun saveText() {
//        val ed: SharedPreferences.Editor = .edit()
//        ed.putString(SAVED_TEXT, etText.getText().toString())
//        ed.commit()

//        App.db.Dao().insert(//запиши модельку, где картинка ткая, тайтл такой и описание такое.
//            ProfileModel(
//                name = binding.etName.text.toString(),
//                email = binding.etEmail.text.toString(),
//                number_phone = binding.etPhoneNumber.text.toString(),
//                gender = binding.etGender.text.toString(),
//                birthday = binding.etDate.text.toString()
//            )
//        )
//    }
//    AuthUI.getInstance().signOut(this).addOnCompleteListener {
//        // do something here
//    }
//    fun loadText() {
//
//    }



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
