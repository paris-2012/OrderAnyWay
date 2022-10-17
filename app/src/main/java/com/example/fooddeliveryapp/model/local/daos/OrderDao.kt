package com.example.fooddeliveryapp.model.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fooddeliveryapp.model.local.entities.OrderItem

@Dao
interface OrderDao {
    @Insert
    fun insertOrderItem(vararg note: OrderItem)
    @Query("Select * FROM MyOrders")
    fun getAllOrderItems(): LiveData<List<OrderItem>>
    @Update
    fun updateOrderItem(vararg note: OrderItem)
    @Delete
    fun deleteOrderItem(vararg note: OrderItem)
}