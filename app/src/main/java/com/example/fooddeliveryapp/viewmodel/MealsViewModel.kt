package com.example.fooddeliveryapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.model.remote.ApiClient
import com.example.fooddeliveryapp.model.remote.ApiService
import com.example.fooddeliveryapp.model.remote.response.CategoryResponse
import com.example.fooddeliveryapp.model.remote.response.IndividualMealResponse
import com.example.fooddeliveryapp.model.remote.response.Meal
import com.example.fooddeliveryapp.model.remote.response.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MealsViewModel : ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    val mealsLiveData = MutableLiveData<MealResponse>()
    val mealLiveData = MutableLiveData<IndividualMealResponse>()
    fun getAllMeals(strCategory: String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        val mealsInfo = apiService.searchByCategory(strCategory)
        mealsInfo.enqueue(object : Callback<MealResponse> {
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                mealsLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e("tag", t.message.toString())
            }
        })
    }

    fun getMealInfo(meal_id: String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        val mealsInfo = apiService.getMealDetails(meal_id)
        mealsInfo.enqueue(object : Callback<MealResponse> {
            override fun onResponse(
                call: Call<MealResponse>,
                response: Response<MealResponse>
            ) {
                mealsLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e("tag", t.message.toString())
            }
        })
    }
}