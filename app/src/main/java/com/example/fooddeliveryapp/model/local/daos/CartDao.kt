package com.example.fooddeliveryapp.model.local.daos

import androidx.room.*
import com.example.fooddeliveryapp.model.local.entities.CartItem

@Dao
interface CartDao {
    @Insert
    fun insertCartItem(vararg note: CartItem)
    @Query("Select * FROM MyCart")
    fun getAllCartItems(): List<CartItem>
    @Update
    fun updateCartItem(vararg note: CartItem)
    @Delete
    fun deleteCartItem(vararg note: CartItem)
}