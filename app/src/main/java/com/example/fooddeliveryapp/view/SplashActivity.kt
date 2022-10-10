package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var bindDeliverySplashActivity: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindDeliverySplashActivity = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bindDeliverySplashActivity.root)

        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.translate)
        bindDeliverySplashActivity.imgBike.apply {
            startAnimation(slideAnim)
        }
        val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotation)
        bindDeliverySplashActivity.imgLogo.apply {
            startAnimation(rotateAnim)
        }

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000L)
    }
}