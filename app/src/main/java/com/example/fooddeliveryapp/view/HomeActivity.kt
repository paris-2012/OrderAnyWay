package com.example.fooddeliveryapp.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivityHomeBinding
import com.example.fooddeliveryapp.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var offersAdapter: OffersAdapter
    private lateinit var offersData: MutableList<Array<String>>
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
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
        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.cart -> {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.my_orders -> {
                    val intent = Intent(this, OrdersActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.favorite -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.tracking -> {
                    val intent = Intent(this, TrackingActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.chat -> {
                    val intent = Intent(this, SupportChatActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.contact -> {
                    val intent = Intent(this, ContactUsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    sharedPreferences = getSharedPreferences("LOGIN_CREDENTIALS", MODE_PRIVATE)
                    editor = sharedPreferences.edit()

                    editor.putString("USERNAME", "")
                    editor.putString("PASSWORD", "")
                    editor.commit()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        binding.btnSearch.setOnClickListener {
            val intent = Intent(this@HomeActivity, MealsActivity::class.java)
            intent.putExtra("name", binding.edtSearchName.text.toString())
            ContextCompat.startActivity(this@HomeActivity, intent, null)
        }
        offersData = mutableListOf<Array<String>>()
        offersData.add(arrayOf("Winner winner \n chicken dinner", "get $4 off any meal that has chicken"))
        offersData.add(arrayOf("happy birthday", "get 30% off your next order"))
        offersData.add(arrayOf("refer a friend", "your friend gets their first meal free"))
        binding.recyclerViewOffers.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        offersAdapter = OffersAdapter(offersData)
        binding.recyclerViewOffers.adapter = offersAdapter
    }
}