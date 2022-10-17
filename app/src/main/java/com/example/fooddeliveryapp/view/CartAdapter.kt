package com.example.fooddeliveryapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CartItemLayoutBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.daos.CartDao
import com.example.fooddeliveryapp.model.local.entities.CartItem
import com.squareup.picasso.Picasso

class CartAdapter(private val notes: ArrayList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    internal lateinit var binding: CartItemLayoutBinding
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao
    override fun getItemCount() = notes.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        binding = CartItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        database = AppDatabase.getInstance(parent.context)
        cartDao = database.cartDao()
        return ViewHolder(binding.root)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: CartItem) {
            binding.apply {
                if (item.img != "promo") {
                    Picasso.get()
                        .load(item.img)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imgProduct)
                    txtPrice.text = "$${item.price}"
                } else {
                    btnAddQuantity.visibility = View.GONE
                    btnRemoveQuantity.visibility = View.GONE
                    txtQuantity.visibility = View.GONE

                    txtPrice.text = "-$${item.price.slice(1..item.price.length-1)}"
                }
                txtTitle.text = item.title
                txtQuantity.text = item.quantity.toString()
            }
            binding.btnAddQuantity.setOnClickListener {
                val patch = CartItem(item.id, item.title, item.price, item.img, item.quantity + 1)
                cartDao.updateCartItem(patch)
                item.quantity++
                binding.txtQuantity.text = (item.quantity).toString()
            }
            binding.btnRemoveQuantity.setOnClickListener {
                if (item.quantity > 0) {
                    val patch = CartItem(item.id, item.title, item.price, item.img, item.quantity - 1)
                    cartDao.updateCartItem(patch)
                    item.quantity--
                    binding.txtQuantity.text = (item.quantity).toString()
                }
            }
            binding.btnDelete.setOnClickListener {
                val patch = CartItem(item.id, item.title, item.price, item.img, item.quantity)
                cartDao.deleteCartItem(patch)
            }
        }
    }
}