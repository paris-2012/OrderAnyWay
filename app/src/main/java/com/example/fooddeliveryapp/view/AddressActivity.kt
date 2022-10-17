package com.example.fooddeliveryapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityAddressBinding
import com.example.fooddeliveryapp.model.local.daos.AddressDao
import com.example.fooddeliveryapp.model.local.entities.AddressItem
import com.example.fooddeliveryapp.model.local.AppDatabase

class AddressActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddressBinding
    private lateinit var adapter: AddressAdapter
    private lateinit var database: AppDatabase
    private lateinit var addressDao: AddressDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getInstance(applicationContext)
        addressDao = database.addressDao()
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }
    private fun initView() {
        val items = intent.getStringExtra("ITEMS")
        binding.recyclerView.layoutManager = LinearLayoutManager(this@AddressActivity)
        adapter = AddressAdapter(ArrayList(addressDao.getAllAddressItems()), this@AddressActivity, items?:"")
        binding.recyclerView.adapter = adapter
        binding.btnAddAddress.setOnClickListener {
            val newAddress = AddressItem(0, binding.edtTitle.text.toString(), binding.edtAddress.text.toString())
            addressDao.insertAddressItem(newAddress)
            adapter = AddressAdapter(ArrayList(addressDao.getAllAddressItems()), this@AddressActivity, items?:"")
            binding.recyclerView.adapter = adapter
        }
    }

}