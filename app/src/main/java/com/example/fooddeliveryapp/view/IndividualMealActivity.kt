package com.example.fooddeliveryapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.ActivityIndividualMealBinding
import com.example.fooddeliveryapp.model.local.AppDatabase
import com.example.fooddeliveryapp.model.local.CartDao
import com.example.fooddeliveryapp.model.local.CartItem
import com.example.fooddeliveryapp.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso

class IndividualMealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndividualMealBinding
    private lateinit var mealsViewModel: MealsViewModel
    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao
    private var quantity = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getInstance(applicationContext)
        cartDao = database.cartDao()
        binding = ActivityIndividualMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
    }

    private fun setUpObserver() {
        mealsViewModel = ViewModelProvider(this)[MealsViewModel::class.java]
        mealsViewModel.getMealInfo(intent.getStringExtra("meal_id")?:"")
        mealsViewModel.mealsLiveData.observe(this) {
            binding.txtTitle.text = it.meals[0].strMeal
            val price = kotlin.math.abs(findPrice(it.meals[0].strMeal))
            val note = CartItem(it.meals[0].idMeal.toLong(),  it.meals[0].strMeal, price.toString(), it.meals[0].strMealThumb, quantity)
            binding.txtPrice.text = "$${price.toString()}"
            Picasso.get()
                .load(it.meals[0].strMealThumb)
                .placeholder(R.drawable.allrounded_edittext)
                .into(binding.imgFood)
            binding.txtRecipe.text = it.meals[0].strInstructions
            binding.txtReadMore.setOnClickListener {
                binding.txtRecipe.maxLines = 1000
            }
            binding.txtCategory.text = it.meals[0].strCategory
            binding.txtCountry.text = it.meals[0].strArea

            binding.btnAddCart.setOnClickListener {
                note.quantity = quantity
                cartDao.insertCartItem(note)
            }
            binding.btnIncrease.setOnClickListener {
                quantity++
                binding.txtQuantity.text = quantity.toString()
                binding.txtPrice.text = (quantity * price).toString()
            }
            binding.btnDecrease.setOnClickListener {
                if (quantity > 0) {
                    quantity--
                }
                binding.txtQuantity.text = quantity.toString()
                binding.txtPrice.text = (quantity * price).toString()
            }
        }
    }

    private fun findPrice(dish: String): Int{
        val dishLower = dish.lowercase()
        var price = 0
        for (i in 0..4) {
            price += dishLower[i].toInt() - 97
        }
        price %= 20
        return price
    }
}