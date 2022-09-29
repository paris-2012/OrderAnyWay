package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.fooddeliveryapp.databinding.ActivityPaymentBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.OrderDao
import com.example.fooddeliveryapp.model.local.OrderItem
import com.example.fooddeliveryapp.view.AddressAdapter.Companion.ADDRESS
import com.example.fooddeliveryapp.viewmodel.CheckoutViewModel

class PaymentActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var paymentViewModel: CheckoutViewModel
    private lateinit var database: AppDatabase
    private lateinit var orderDao: OrderDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        paymentViewModel = CheckoutViewModel(application)
        database = AppDatabase.getInstance(this)
        orderDao = database.orderDao()
        val userId = intent.getStringExtra(USER_ID)
        val address = intent.getStringExtra(ADDRESS)
        binding.btnConfirm.setOnClickListener {
            val paymentId = binding.radioPayment.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(paymentId)
            val paymentMethod = radioButton.text.toString()
            //change to observe
            val order = OrderItem(0, paymentViewModel.getPriceOfCart().toString(), address?:"", paymentMethod)
            orderDao.insertOrderItem(order)
            intent.putExtra(USER_ID, userId)
            val intent = Intent(this, OrdersActivity::class.java)
            ContextCompat.startActivity(this, intent, null)
        }
    }
    companion object {
        const val USER_ID = "user_id"
    }
}