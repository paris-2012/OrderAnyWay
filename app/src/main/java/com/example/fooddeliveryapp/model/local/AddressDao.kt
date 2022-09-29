package com.example.fooddeliveryapp.model.local

import androidx.room.*

@Dao
interface AddressDao {
    @Insert
    fun insertAddressItem(vararg note: AddressItem)
    @Query("Select * FROM MyAddresses")
    fun getAllAddressItems(): List<AddressItem>
    @Update
    fun updateAddressItem(vararg note: AddressItem)
    @Delete
    fun deleteAddressItem(vararg note: AddressItem)
}