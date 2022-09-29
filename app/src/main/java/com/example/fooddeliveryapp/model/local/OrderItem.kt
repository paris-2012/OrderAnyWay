package com.example.fooddeliveryapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyOrders")
data class OrderItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "total") val total: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "payment") var payment: String

)