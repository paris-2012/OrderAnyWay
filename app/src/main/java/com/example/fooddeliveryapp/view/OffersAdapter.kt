package com.example.fooddeliveryapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.databinding.OffersItemBinding

class OffersAdapter(private val categories: MutableList<Array<String>>) :
    RecyclerView.Adapter<OffersAdapter.ViewHolder>() {
    internal lateinit var binding: OffersItemBinding
    override fun getItemCount() = categories.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.ViewHolder {
        binding = OffersItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(item: Array<String>) {
            binding.apply {
                txtTitle.text = item[0]
                txtContent.text = item[1]
            }
        }
    }
}