package com.example.fooddeliveryapp.model.local.daos

import androidx.room.*
import com.example.fooddeliveryapp.model.local.entities.AddressItem

@Dao
interface AddressDao {
    @Insert
    fun insertAddressItem(vararg note: AddressItem)

    @Query("Select * FROM MyAddresses")
    fun getAllAddressItems(): List<AddressItem>

    @Query("SELECT * FROM MyAddresses WHERE id = :id")
    fun findAddressById(id: Long): AddressItem?

    @Update
    fun updateAddressItem(vararg note: AddressItem)

    @Delete
    fun deleteAddressItem(vararg note: AddressItem)
}