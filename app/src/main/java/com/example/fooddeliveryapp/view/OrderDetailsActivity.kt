package com.example.fooddeliveryapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.databinding.ActivityOrderDetailsBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.entities.OrderItem
import com.example.fooddeliveryapp.viewmodel.OrderVMFactory
import com.example.fooddeliveryapp.viewmodel.OrderViewModel

class OrderDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailsBinding
    lateinit var viewModel: OrderViewModel
    //get the the order id thru intent
    //query the order id in roomdb
    //set the elements to query items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        val intent = getIntent()
        val orderId = intent.getStringExtra("orderId")?.toLong()
        val factory = OrderVMFactory(AppDatabase.getInstance(baseContext))
        viewModel = ViewModelProvider(this, factory)[OrderViewModel::class.java]
        viewModel.orders.observe(this) {
            var myOrder = OrderItem(0, "", "", "", "", "", "")
            for (order in it) {
                if (order.id == orderId) {
                    myOrder = order
                }
            }
            binding.txtIdTitle.text = myOrder.id.toString()
            binding.txtOrderItemTitle.text = myOrder.items
        }


    }
}