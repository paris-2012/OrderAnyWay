package com.example.fooddeliveryapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooddeliveryapp.databinding.ActivityMealsBinding
import com.example.fooddeliveryapp.viewmodel.MealsViewModel

class MealsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealsBinding
    private lateinit var mealViewModel: MealsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        initView()
    }

    private fun setUpObserver() {
        mealViewModel = ViewModelProvider(this)[MealsViewModel::class.java]
        mealViewModel.getAllMeals(intent.getStringExtra("category")?:"")
        mealViewModel.mealsLiveData.observe(this){
            binding.recyclerViewMeals.layoutManager = GridLayoutManager(this@MealsActivity, 2)
            binding.recyclerViewMeals.adapter = MealsAdapter(it.meals, this@MealsActivity)
        }
    }
    private fun initView() {
    }
}