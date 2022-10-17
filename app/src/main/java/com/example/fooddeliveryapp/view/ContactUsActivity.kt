package com.example.fooddeliveryapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddeliveryapp.databinding.ActivityContactUsBinding

class ContactUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        binding.apply {
            emptystar1.setOnClickListener{
                fullstar1.visibility = View.VISIBLE
                fullstar2.visibility = View.GONE
                fullstar3.visibility = View.GONE
                fullstar4.visibility = View.GONE
                fullstar5.visibility = View.GONE
                txtThanks.visibility = View.VISIBLE
            }

            emptystar2.setOnClickListener{
                fullstar1.visibility = View.VISIBLE
                fullstar2.visibility = View.VISIBLE
                fullstar3.visibility = View.GONE
                fullstar4.visibility = View.GONE
                fullstar5.visibility = View.GONE
                txtThanks.visibility = View.VISIBLE
            }
            emptystar3.setOnClickListener{
                fullstar1.visibility = View.VISIBLE
                fullstar2.visibility = View.VISIBLE
                fullstar3.visibility = View.VISIBLE
                fullstar4.visibility = View.GONE
                fullstar5.visibility = View.GONE
                txtThanks.visibility = View.VISIBLE
            }
            emptystar4.setOnClickListener{
                fullstar1.visibility = View.VISIBLE
                fullstar2.visibility = View.VISIBLE
                fullstar3.visibility = View.VISIBLE
                fullstar4.visibility = View.VISIBLE
                fullstar5.visibility = View.GONE
                txtThanks.visibility = View.VISIBLE
            }
            emptystar5.setOnClickListener{
                fullstar1.visibility = View.VISIBLE
                fullstar2.visibility = View.VISIBLE
                fullstar3.visibility = View.VISIBLE
                fullstar4.visibility = View.VISIBLE
                fullstar5.visibility = View.VISIBLE
                txtThanks.visibility = View.VISIBLE
            }
        }

    }
}