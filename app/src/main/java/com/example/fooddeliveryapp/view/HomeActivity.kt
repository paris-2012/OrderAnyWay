package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityHomeBinding
import com.example.fooddeliveryapp.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var offersData: MutableList<Array<String>>
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        initView()
    }

    private fun setUpObserver() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.getAllCategory()
        homeViewModel.categoryLiveData.observe(this){
            binding.recyclerViewCategories.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewCategories.adapter = CategoriesAdapter(it.categories, this@HomeActivity)
        }
    }
    private fun initView() {
        offersData = mutableListOf<Array<String>>()
        offersData.add(arrayOf("Winner winner \n chicken dinner", "get $4 off any meal that has chicken"))
        offersData.add(arrayOf("happy birthday", "get 30% off your next order"))
        offersData.add(arrayOf("refer a friend", "your friend gets their first meal free"))
        binding.btnContactUs.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }
        binding.btnTracking.setOnClickListener {
            val intent = Intent(this, TrackingActivity::class.java)
            startActivity(intent)
        }
        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.recyclerViewOffers.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        offersAdapter = OffersAdapter(offersData)
        binding.recyclerViewOffers.adapter = offersAdapter
        binding.btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}