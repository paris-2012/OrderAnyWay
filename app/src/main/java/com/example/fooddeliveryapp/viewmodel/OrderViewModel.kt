package com.example.fooddeliveryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.entities.OrderItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel(private val db: AppDatabase): ViewModel() {
    val orders = db.orderDao.getAllOrderItems()
    val msg = MutableLiveData<String>()

    fun addOrder(order: OrderItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val orderId = db.orderDao.insertOrderItem(order)
        }
    }
}