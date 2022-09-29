package com.example.fooddeliveryapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        val email = "binding.edtEmail.text.toString()"
        val password = "binding.edtPassword.text.toString()"
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {
                        currentUser = auth.currentUser as FirebaseUser
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