package com.example.fooddeliveryapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddeliveryapp.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("PROFILE_INFO", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.edtName.setText(sharedPreferences.getString("NAME", ""))
        binding.edtEmail.setText(sharedPreferences.getString("EMAIL", ""))
        binding.edtPhone.setText(sharedPreferences.getString("PHONE", ""))
        binding.edtLocation.setText(sharedPreferences.getString("LOCATION", ""))
        binding.btnConfirm.setOnClickListener {
            editor.apply {
                putString("NAME", binding.edtName.text.toString())
                putString("EMAIL", binding.edtEmail.text.toString())
                putString("PHONE", binding.edtPhone.text.toString())
                putString("LOCATION", binding.edtLocation.text.toString())
                editor.commit()
            }

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}