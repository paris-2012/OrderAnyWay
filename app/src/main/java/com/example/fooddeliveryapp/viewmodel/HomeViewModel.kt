package com.example.fooddeliveryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.model.remote.ApiClient
import com.example.fooddeliveryapp.model.remote.ApiService
import com.example.fooddeliveryapp.model.remote.response.CategoryResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeViewModel(val apiService: ApiService) : ViewModel() {

    private val processing = MutableLiveData<Boolean>()
    val categoryLiveData = MutableLiveData<CategoryResponse>()
    private val error = MutableLiveData<String>()

    fun getAllCategory(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                processing.postValue(true)
                val response: Response<CategoryResponse> = apiService.getCategoryInfo()
                processing.postValue(false)
                val result = response.body()

                if(result == null) {
                    error.postValue("Empty result from server.")
                    return@launch
                }

                categoryLiveData.postValue(result)

            } catch (e: Exception) {
                processing.postValue(false)
                error.postValue(e.toString())
            }
        }
    }
}