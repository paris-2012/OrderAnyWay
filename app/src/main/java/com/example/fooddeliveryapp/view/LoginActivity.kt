package com.example.fooddeliveryapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fooddeliveryapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.testSkip.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.btnEmailLogin.setOnClickListener {
            binding.phoneBlock.visibility = View.GONE
            binding.emailBlock.visibility = View.VISIBLE
            binding.googleBlock.visibility = View.GONE
        }
        binding.btnPhoneLogin.setOnClickListener {
            binding.phoneBlock.visibility = View.VISIBLE
            binding.emailBlock.visibility = View.GONE
            binding.googleBlock.visibility = View.GONE
        }
        binding.btnGoogleLogin.setOnClickListener {
            binding.googleBlock.visibility = View.VISIBLE
            binding.emailBlock.visibility = View.GONE
            binding.phoneBlock.visibility = View.GONE
        }
        binding.btnSubmitEmail.setOnClickListener {
            loginAccount()
        }
    }
    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        if (this::currentUser.isInitialized) {
            if (auth.currentUser != null && currentUser != auth) {
                currentUser = auth.currentUser as FirebaseUser
            }
        }
    }
    private fun loginAccount() {
        val email = binding.edtEmailEmail.text.toString()
        val password = binding.edtPasswordEmail.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {
                        currentUser = auth.currentUser as FirebaseUser

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        ContextCompat.startActivity(this@LoginActivity, intent, null)
                    }
                } else {
                    Log.i("tag", "error did not login successfully!")
                    makeToast(this@LoginActivity, "Login Failed!")
                }

            }
    }
    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }
}