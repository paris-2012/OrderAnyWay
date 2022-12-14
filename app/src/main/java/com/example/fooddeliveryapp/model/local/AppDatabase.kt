package com.example.fooddeliveryapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fooddeliveryapp.model.local.daos.AddressDao
import com.example.fooddeliveryapp.model.local.daos.CartDao
import com.example.fooddeliveryapp.model.local.daos.FavoriteDao
import com.example.fooddeliveryapp.model.local.daos.OrderDao
import com.example.fooddeliveryapp.model.local.entities.AddressItem
import com.example.fooddeliveryapp.model.local.entities.CartItem
import com.example.fooddeliveryapp.model.local.entities.FavoriteItem
import com.example.fooddeliveryapp.model.local.entities.OrderItem

@Database(entities = [CartItem::class, AddressItem::class, OrderItem::class, FavoriteItem::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun addressDao(): AddressDao
    abstract val orderDao: OrderDao
    abstract fun favoriteDao(): FavoriteDao
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "userDb")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}