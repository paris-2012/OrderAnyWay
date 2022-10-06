package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
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
        val address = intent.getStringExtra(ADDRESS)
        binding.radioBtnScanQR.setOnClickListener {
            binding.imgPaypal.visibility = View.VISIBLE
        }
        binding.radioBtnCash.setOnClickListener {
            binding.imgPaypal.visibility = View.GONE
        }
        binding.radioBtnCredit.setOnClickListener {
            binding.imgPaypal.visibility = View.GONE
        }
        binding.radioBtnGooglePay.setOnClickListener {
            binding.imgPaypal.visibility = View.GONE
        }

        binding.btnConfirm.setOnClickListener {
            val paymentId = binding.radioPayment.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(paymentId)
            val paymentMethod = radioButton.text.toString()
            paymentViewModel.getPriceOfCart()
            paymentViewModel.finalTotal.observe(this){
                val order = OrderItem(0, it.toString(), address?:"", paymentMethod)
                orderDao.insertOrderItem(order)
            }
            val intent = Intent(this, OrdersActivity::class.java)
            ContextCompat.startActivity(this, intent, null)
        }
    }

}