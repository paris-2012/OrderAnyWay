package com.example.fooddeliveryapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.AddressListItemBinding
import com.example.fooddeliveryapp.model.local.AddressDao
import com.example.fooddeliveryapp.model.local.AddressItem
import com.example.fooddeliveryapp.model.local.AppDatabase

class AddressAdapter(private val categories: ArrayList<AddressItem>, context: Context) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    private lateinit var database: AppDatabase
    private lateinit var addressDao: AddressDao
    internal lateinit var binding: AddressListItemBinding
    val myContext = context
    override fun getItemCount() = categories.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        binding = AddressListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: AddressItem) {
            database = AppDatabase.getInstance(myContext)
            addressDao = database.addressDao()

            binding.txtTitle.text = item.location
            binding.txtAddress.text = item.address
            binding.btnSelect.setOnClickListener {
                val intent = Intent(myContext, PaymentActivity::class.java)
                intent.putExtra(ADDRESS, item.address)
                ContextCompat.startActivity(myContext, intent, null)
            }
            binding.btnDelete.setOnClickListener {
                addressDao.deleteAddressItem(item)
            }
        }
    }
    companion object {
        const val ADDRESS = "address"
    }
}