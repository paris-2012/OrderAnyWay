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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MealsViewModel : ViewModel() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: ApiService
    val mealsLiveData = MutableLiveData<MealResponse>()
    fun getAllMealsByCategory(strCategory: String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        val mealsInfo = apiService.searchByCategory(strCategory).subscribeOn(
            Schedulers.io()
        ).doOnSubscribe {

        }.observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(
            { response -> mealsLiveData.postValue(response)},
            { t -> t.printStackTrace() }
        )
    }
    fun findPrice(dish: String): Int{
        val dishLower = dish.lowercase()
        var price = 0
        for (i in 0..4) {
            price += dishLower[i].toInt() - 97
        }
        price %= 20
        return price
    }
    fun getAllMealsByName(strName: String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        val mealsInfo = apiService.searchByName(strName).subscribeOn(
            Schedulers.io()
        ).doOnSubscribe {

        }.observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(
            { response -> mealsLiveData.postValue(response)},
            { t -> t.printStackTrace() }
        )
    }

    fun getMealInfo(meal_id: String){
        retrofit = ApiClient.getRetrofit()
        apiService = retrofit.create(ApiService::class.java)

        val mealsInfo = apiService.getMealDetails(meal_id).subscribeOn(
            Schedulers.io()
        ).doOnSubscribe {

        }.observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe(
            { response -> mealsLiveData.postValue(response)},
            { t -> t.printStackTrace() }
        )
    }
}