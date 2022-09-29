package com.example.fooddeliveryapp.model.local

import androidx.room.*

@Dao
interface OrderDao {
    @Insert
    fun insertOrderItem(vararg note: OrderItem)
    @Query("Select * FROM MyOrders")
    fun getAllOrderItems(): List<OrderItem>
    @Update
    fun updateOrderItem(vararg note: OrderItem)
    @Delete
    fun deleteOrderItem(vararg note: OrderItem)
}