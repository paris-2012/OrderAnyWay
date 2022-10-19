package com.example.fooddeliveryapp.model.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fooddeliveryapp.model.local.entities.AddressItem
import com.example.fooddeliveryapp.model.local.entities.OrderItem

@Dao
interface OrderDao {
    @Insert
    fun insertOrderItem(vararg note: OrderItem)

    @Query("Select * FROM MyOrders")
    fun getAllOrderItems(): LiveData<List<OrderItem>>

    @Query("SELECT * FROM MyOrders WHERE id = :id")
    fun findOrderById(id: Long): OrderItem?

    @Update
    fun updateOrderItem(vararg note: OrderItem)

    @Delete
    fun deleteOrderItem(vararg note: OrderItem)
}