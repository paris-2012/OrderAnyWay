package com.example.fooddeliveryapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fooddeliveryapp.databinding.ActivityRegisterBinding
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
            binding.googleBlock.visibility = View.GONE
        }
        binding.btnPhoneRegistration.setOnClickListener {
            binding.phoneBlock.visibility = View.VISIBLE
            binding.emailBlock.visibility = View.GONE
            binding.googleBlock.visibility = View.GONE
        }
        binding.btnGoogleRegistration.setOnClickListener {
            binding.googleBlock.visibility = View.VISIBLE
            binding.emailBlock.visibility = View.GONE
            binding.phoneBlock.visibility = View.GONE
        }

        binding.btnSubmitEmail.setOnClickListener {
            addEmailAccount(binding.edtEmailEmail.text.toString(), binding.edtPasswordEmail.text.toString(), this@RegistrationActivity)
        }

        binding.btnSendOTPPhone.setOnClickListener {
            val phoneNum = "+1" + binding.edtPhonePhone.text.toString()
            Log.i("phone", phoneNum)
            //viewModel.sendOTP(phoneNum)
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
    fun addEmailAccount(email: String, password: String, context: Context) {
        val auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {
                    }
                    auth.currentUser.let {
                        auth.currentUser?.sendEmailVerification()
                    }
                    makeToast(context, "Account created successfully")
                } else {
                    Log.i("tag", "error did not create successfully!")
                    makeToast(context, "Account not created successfully")
                }
            }
    }



    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }
}