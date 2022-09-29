package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivityRegisterBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnEmailRegistration.setOnClickListener {
            replaceFragment(EmailRegistrationFragment(this), R.id.registrationContainer)
        }
        binding.btnPhoneRegistration.setOnClickListener {
            replaceFragment(PhoneRegistrationFragment(this), R.id.registrationContainer)
        }
        binding.txtLinkToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment, container: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(container, fragment)
            .commit()
    }
}