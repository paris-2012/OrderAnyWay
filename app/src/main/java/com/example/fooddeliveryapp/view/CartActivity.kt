package com.example.fooddeliveryapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityCartBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.daos.CartDao
import com.example.fooddeliveryapp.model.local.entities.CartItem


class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao
    private lateinit var cartAdapter: CartAdapter
    private var cartString = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    private fun initView() {
        database = AppDatabase.getInstance(applicationContext)
        cartDao = database.cartDao()
        binding.recyclerView.layoutManager = LinearLayoutManager(this@CartActivity)
        val availablePromos = hashMapOf<String, Array<String>>()
        availablePromos.put("FREEFOOD", arrayOf("-5", "1"))
        availablePromos.put("FREELUNCH", arrayOf("-10", "2"))
        binding.btnPromoCode.setOnClickListener {
            if (availablePromos.containsKey(binding.edtPromoCode.text.toString())) {
                val promo = CartItem(availablePromos[binding.edtPromoCode.text.toString()]!![1].toLong(),  binding.edtPromoCode.text.toString(), availablePromos[binding.edtPromoCode.text.toString()]!![0], "promo", 1)
                cartDao.insertCartItem(promo)
                fetchCart()
            }
        }
        fetchCart()
        binding.btnConfirm.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            intent.putExtra("ITEMS", cartString)
            startActivity(intent)
        }
    }

        private fun fetchCart() {
            val cartList = cartDao.getAllCartItems()
            for (food in cartList) {
                cartString += food.title
                cartString += "\n"
            }
            cartAdapter = CartAdapter(ArrayList(cartList))
            binding.recyclerView.adapter = cartAdapter
    }

}