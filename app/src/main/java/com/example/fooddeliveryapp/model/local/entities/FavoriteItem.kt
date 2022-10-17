package com.example.fooddeliveryapp.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyFavorite")
data class FavoriteItem(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "item") val title: String,
    @ColumnInfo(name = "pictures") val img: String
)