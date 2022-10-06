package com.example.fooddeliveryapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fooddeliveryapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LOGIN_CREDENTIALS", MODE_PRIVATE)
        val username = sharedPreferences.getString("USERNAME", "")?:""
        val password = sharedPreferences.getString("PASSWORD", "")?:""
        if (username!="" && password!="") {
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (auth.currentUser != null) {
                            currentUser = auth.currentUser as FirebaseUser

                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            ContextCompat.startActivity(this@MainActivity, intent, null)
                        }
                    } else {
                        Log.i("tag", "error did not login successfully!")
                        makeToast(this@MainActivity, "Login Failed!")
                    }

                }
        }
        binding.btnRegisterActivity.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.btnLoginActivity.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun makeToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }

}