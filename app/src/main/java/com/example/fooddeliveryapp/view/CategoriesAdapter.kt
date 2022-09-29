package com.example.fooddeliveryapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.CategoriesItemBinding
import com.example.fooddeliveryapp.model.remote.response.Category
import com.squareup.picasso.Picasso

class CategoriesAdapter(private val categories: List<Category>, private val context: Context) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    internal lateinit var binding: CategoriesItemBinding
    override fun getItemCount() = categories.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesAdapter.ViewHolder {
        binding = CategoriesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(category: Category) {
            binding.txtTitle.text = category.strCategory
            Picasso.get()
                .load(category.strCategoryThumb)
                .placeholder(R.drawable.allrounded_edittext)
                .into(binding.imgCategory)
            binding.categoryLink.setOnClickListener {
                val intent = Intent(context, MealsActivity::class.java)
                intent.putExtra("category", category.strCategory)
                startActivity(context, intent, null)
            }
        }
    }
}