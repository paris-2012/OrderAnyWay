package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityCartBinding
import com.example.fooddeliveryapp.databinding.ActivityOrdersBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.CartDao
import com.example.fooddeliveryapp.model.local.CartItem
import com.example.fooddeliveryapp.model.local.OrderDao


class OrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    private lateinit var database: AppDatabase
    private lateinit var orderDao: OrderDao
    private lateinit var orderAdapter: OrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        database = AppDatabase.getInstance(applicationContext)
        orderDao = database.orderDao()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@OrdersActivity, LinearLayoutManager.HORIZONTAL, false)
        val userId = intent.getStringExtra("user_id")
        binding.btnReturnToHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }
        orderAdapter = OrdersAdapter(ArrayList(orderDao.getAllOrderItems()))
        binding.recyclerView.adapter = orderAdapter
    }

}