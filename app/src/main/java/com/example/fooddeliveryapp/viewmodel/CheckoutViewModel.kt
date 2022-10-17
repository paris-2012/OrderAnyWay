package com.example.fooddeliveryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.daos.CartDao

class CheckoutViewModel(val app: Application): AndroidViewModel(app) {
    private lateinit var cartDao: CartDao
    private lateinit var database: AppDatabase
    val finalTotal = MutableLiveData<Int>()

    fun getPriceOfCart() {
        database = AppDatabase.getInstance(app.applicationContext)
        cartDao = database.cartDao()
        val items = cartDao.getAllCartItems()
        var total = 0
        for (cartItem in items) {
            total += cartItem.price.toInt()
        }
        finalTotal.postValue(total)
    }
}