package com.example.bottomnavigation41.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigation41.databinding.FragmentAuthBinding
import com.example.bottomnavigation41.showToast
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {

    lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null
//    val view: SmsConfirmationView = binding.smsCodeView
    val credential = correctCode?.let { it1 -> PhoneAuthProvider.getCredential(it1, binding.etCode.toString()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(inflater, container, false)


        initListener()
        return binding.root
    }


    private fun initListener() {
        binding.btnSend.setOnClickListener {
            if (binding.etPhone.text!!.isNotEmpty()) {
                sendPhone()
                Toast.makeText(requireContext(), "sending", Toast.LENGTH_SHORT).show()
                //отправляемся в FireBase
            } else {
                binding.etLayoutPhone.error = "введите номер телефона"
            }
            binding.btnConfirm.setOnClickListener {
                sendCode()
            }
            val view: SmsConfirmationView = binding.smsCodeView
            view.onChangeListener = SmsConfirmationView.OnChangeListener { _, _ ->
                val credential = correctCode?.let { it1 -> PhoneAuthProvider.getCredential(it1,
                    binding.smsCodeView.toString()
                ) }


            }

        }
    }



    private fun sendPhone() {
        auth.setLanguageCode("RU")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber( binding.etPhone.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    showToast(p0.message.toString())
                }

                override fun onCodeSent(
                    vericationCode: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    correctCode = vericationCode
                    binding.etLayoutPhone.isVisible = false
                    binding.btnSend.isVisible = false

//                    binding.etLayoutCode.isVisible = true
                    binding.btnConfirm.isVisible = true
                    binding.smsCodeView.isVisible = true
                    Log.e("ololo", "onCodeSent: $vericationCode")
                    super.onCodeSent(vericationCode, p1)
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun sendCode() {

        val credential = correctCode?.let { it1 ->
            PhoneAuthProvider.getCredential(
                it1, binding.etCode.text.toString()
            )
        }
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }



    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(com.example.bottomnavigation41.R.id.navigation_home)

                } else {
                    Log.w("ololo", "signInWithPhoneAuthCredential: failure ", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.e(
                            "ololo",
                            "signInWithPhoneAuthCredential:" + task.exception.toString(),
                        )
                    }
                }
            }
    }


}


