package com.example.fooddeliveryapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivityRegisterBinding
import com.example.fooddeliveryapp.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnEmailRegistration.setOnClickListener {
            binding.phoneBlock.visibility = View.GONE
            binding.emailBlock.visibility = View.VISIBLE
        }
        binding.btnPhoneRegistration.setOnClickListener {
            binding.phoneBlock.visibility = View.VISIBLE
            binding.emailBlock.visibility = View.GONE
        }

        val viewModel = com.example.fooddeliveryapp.viewmodel.AuthenticationViewModel(this)
        binding.btnSubmitEmail.setOnClickListener {
            viewModel.addEmailAccount(binding.edtEmailEmail.text.toString(), binding.edtPasswordEmail.text.toString(), this@RegistrationActivity)
        }

        binding.btnSendOTPPhone.setOnClickListener {
            val phoneNum = "+1" + binding.edtPhonePhone.text.toString()
            Log.i("phone", phoneNum)
            viewModel.sendOTP(phoneNum)
        }
        binding.btnSubmitPhone.setOnClickListener {
            val phoneNum = "+1" + binding.edtPhonePhone.text.toString()
            Log.i("phone", phoneNum)
            addPhoneAccount(phoneNum, binding.edtPasswordPhone.text.toString())
        }

        binding.txtLinkToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun addPhoneAccount(phoneNumber: String, otp: String) {
        val auth = Firebase.auth
        auth.signInWithCredential(PhoneAuthProvider.getCredential(phoneNumber, otp))
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    makeToast(this.applicationContext, "Login Success")
                    val intent = Intent(this.applicationContext, HomeActivity::class.java)
                    ContextCompat.startActivity(this.applicationContext, intent, null)
                } else {
                    makeToast(this.applicationContext, "Login Failed")
                }
            }
    }

    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }
}