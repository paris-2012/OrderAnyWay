package com.example.fooddeliveryapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddeliveryapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProfile()
        binding.btnReturnToHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getProfile() {
        sharedPreferences = getSharedPreferences("PROFILE_INFO", MODE_PRIVATE)
        binding.txtName.text = sharedPreferences.getString("NAME", "")
        binding.txtEmail.text = sharedPreferences.getString("EMAIL", "")
        binding.txtPhone.text = sharedPreferences.getString("PHONE", "")
        binding.txtLocation.text = sharedPreferences.getString("LOCATION", "")
    }
}