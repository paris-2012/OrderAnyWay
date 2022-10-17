package com.example.fooddeliveryapp.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.OrdersItemBinding
import com.example.fooddeliveryapp.model.local.entities.OrderItem

class OrdersAdapter(private val orders: List<OrderItem>, private val context: Context) :
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
            binding.btnTrack.setOnClickListener {
                val intent = Intent(context, TrackingActivity::class.java)
                startActivity(context, intent, null)
            }
            binding.btnReceipt.setOnClickListener {
                val intent = Intent(context, OrderDetailsActivity::class.java)

                intent.putExtra("orderId", order.id.toString())
                startActivity(context, intent, null)
            }
            var itemsMod = order.items.replace("\n".toRegex(), ", ")
            if (itemsMod.length > 40) {
                itemsMod = itemsMod.slice(0..40)
                itemsMod += "...  "
            }
            itemsMod = itemsMod.slice(0..itemsMod.length - 3)
            binding.txtTitle.text = "Global Food Court"
            binding.txtItems.text = itemsMod
            binding.txtDate.text = order.time
            binding.txtPrice.text = "$ ${order.total}"
        }
    }
}

