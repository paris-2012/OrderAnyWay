package com.example.fooddeliveryapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyCart")
data class CartItem(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "item") val title: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "pictures") val img: String,
    @ColumnInfo(name = "quantity") var quantity: Int
)