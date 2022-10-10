package com.example.fooddeliveryapp.model.remote


import com.example.fooddeliveryapp.model.remote.Constants.END_POINT_CATEGORIES
import com.example.fooddeliveryapp.model.remote.Constants.FILTER_BY_CATEGORY
import com.example.fooddeliveryapp.model.remote.Constants.MEAL_ID_ENDPOINT
import com.example.fooddeliveryapp.model.remote.Constants.MEAL_NAME_ENDPOINT
import com.example.fooddeliveryapp.model.remote.response.CategoryResponse
import com.example.fooddeliveryapp.model.remote.response.IndividualMealResponse
import com.example.fooddeliveryapp.model.remote.response.Meal
import com.example.fooddeliveryapp.model.remote.response.MealResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(END_POINT_CATEGORIES)
    fun getCategoryInfo(): Single<CategoryResponse>

    @GET(FILTER_BY_CATEGORY)
    fun searchByCategory(@Query("c") strCategory: String): Single<MealResponse>

    @GET(MEAL_ID_ENDPOINT)
    fun getMealDetails(@Query("i") strCategory: String): Single<MealResponse>

    @GET(MEAL_NAME_ENDPOINT)
    fun searchByName(@Query("s") strName: String): Single<MealResponse>
    //completable no result expected
    //single result expected compulsory success or failure
    //maybe either success or failure
    //flowable heavy data incoming
    //observable simplest form
}