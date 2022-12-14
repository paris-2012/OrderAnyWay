package com.example.fooddeliveryapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityFavoriteBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.daos.FavoriteDao

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var database: AppDatabase
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var favoriteAdapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        database = AppDatabase.getInstance(applicationContext)
        favoriteDao = database.favoriteDao()
        binding.recyclerView.layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
        favoriteAdapter = FavoriteAdapter(ArrayList(favoriteDao.getAllFavoriteItems()), this@FavoriteActivity)
        binding.recyclerView.adapter = favoriteAdapter

    }
}