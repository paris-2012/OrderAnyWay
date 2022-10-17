package com.example.fooddeliveryapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityOrdersBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.viewmodel.OrderVMFactory
import com.example.fooddeliveryapp.viewmodel.OrderViewModel


class OrdersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrdersBinding
    lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setupObservers()
    }

    private fun initViewModel() {
        val factory = OrderVMFactory(AppDatabase.getInstance(baseContext))
        viewModel = ViewModelProvider(this, factory)[OrderViewModel::class.java]
    }
    private fun setupObservers() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this@OrdersActivity, LinearLayoutManager.VERTICAL, false)
        viewModel.msg.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.orders.observe(this) {
            val adapter = OrdersAdapter(it.reversed(), this@OrdersActivity)
            binding.recyclerView.adapter = adapter
        }
    }
    //track orders see details

}