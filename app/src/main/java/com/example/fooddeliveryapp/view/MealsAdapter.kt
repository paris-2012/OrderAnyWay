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
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.entities.FavoriteItem
import com.example.fooddeliveryapp.model.remote.response.Meal
import com.squareup.picasso.Picasso

class MealsAdapter(private val meals: List<Meal>, private val context: Context) :
    RecyclerView.Adapter<MealsAdapter.ViewHolder>() {
    internal lateinit var binding: MealsItemBinding
    override fun getItemCount() = meals.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsAdapter.ViewHolder {
        binding = MealsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding.root)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("Range")
        fun bind(meal: Meal) {
            binding.txtTitle.text = meal.strMeal
            Picasso.get()
                .load(meal.strMealThumb)
                .placeholder(R.drawable.allrounded_edittext)
                .into(binding.imgMeal)
            binding.categoryLink.setOnClickListener{
                val intent = Intent(context, IndividualMealActivity::class.java)
                intent.putExtra("meal_id", meal.idMeal)
                ContextCompat.startActivity(context, intent, null)
            }
            binding.btnFav.setOnClickListener {
                val database = AppDatabase.getInstance(context)
                val favoriteDao = database.favoriteDao()
                favoriteDao.insertFavoriteItem(FavoriteItem(meal.idMeal.toLong(), meal.strMeal, meal.strMealThumb)                )
            }
        }
    }
}