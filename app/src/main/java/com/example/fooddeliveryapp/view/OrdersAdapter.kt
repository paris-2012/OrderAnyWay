package com.example.fooddeliveryapp.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.MealsItemBinding
import com.example.fooddeliveryapp.databinding.OrdersItemBinding
import com.example.fooddeliveryapp.model.local.OrderItem
import com.example.fooddeliveryapp.model.remote.response.Meal
import com.squareup.picasso.Picasso

class OrdersAdapter(private val orders: List<OrderItem>) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    internal lateinit var binding: OrdersItemBinding
    override fun getItemCount() = orders.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.ViewHolder {
        binding = OrdersItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(order: OrderItem) {
            binding.txtTitle.text = "Order Id: ${order.id.toString()}"
            binding.txtAddress.text = order.address
            binding.txtPayment.text = order.payment
            binding.txtPrice.text = "$ ${order.total}"
        }
    }
}