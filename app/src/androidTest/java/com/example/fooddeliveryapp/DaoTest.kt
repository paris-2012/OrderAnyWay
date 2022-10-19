package com.example.fooddeliveryapp

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.daos.AddressDao
import com.example.fooddeliveryapp.model.local.daos.CartDao
import com.example.fooddeliveryapp.model.local.daos.OrderDao
import com.example.fooddeliveryapp.model.local.entities.AddressItem
import com.example.fooddeliveryapp.model.local.entities.CartItem
import com.example.fooddeliveryapp.model.local.entities.OrderItem
import com.example.fooddeliveryapp.model.remote.response.Meal
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var testDatabase: AppDatabase
    private lateinit var addressDao: AddressDao

    private lateinit var orderDao: OrderDao
    private lateinit var cartDao: CartDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        testDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        addressDao = testDatabase.addressDao()
        orderDao = testDatabase.orderDao
        cartDao = testDatabase.cartDao()

    }

    @Test
    fun testSaveAddress() {
        val addressId = 100.toLong()
        val testAddress = AddressItem(addressId, "home", "123 easy street, chapel hill, nc")

        addressDao.insertAddressItem(testAddress)
        val saveAddress = addressDao.findAddressById(addressId)

        Assert.assertNotNull(saveAddress)
        Assert.assertEquals(testAddress, saveAddress)
    }

    @Test
    fun testSaveOrder() {
        val testOrder = OrderItem(100, "1", "123 easy street, chapel hill, nc", "credit card", "12:00", "chicken nuggets", "pending")
        orderDao.insertOrderItem(testOrder)
        val saveOrder = orderDao.findOrderById(100)
        Assert.assertNotNull(saveOrder)
        Assert.assertEquals(testOrder, saveOrder)
    }

    @Test
    fun testSaveMeal() {
        val testMeal = CartItem(100, "chicken nuggets", "5", "imgLink.com", 1)
        cartDao.insertCartItem(testMeal)
        val saveMeal = cartDao.findMealById(100)
        Assert.assertEquals(saveMeal, testMeal)
    }
}