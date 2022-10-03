package com.example.fooddeliveryapp.model.local

import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert
    fun insertFavoriteItem(vararg note: FavoriteItem)
    @Query("Select * FROM MyFavorite")
    fun getAllFavoriteItems(): List<FavoriteItem>
    @Update
    fun updateFavoriteItem(vararg note: FavoriteItem)
    @Delete
    fun deleteFavoriteItem(vararg note: FavoriteItem)
}