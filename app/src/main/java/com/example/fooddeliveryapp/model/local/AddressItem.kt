package com.example.fooddeliveryapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyAddresses")
data class AddressItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "address") val address: String
)