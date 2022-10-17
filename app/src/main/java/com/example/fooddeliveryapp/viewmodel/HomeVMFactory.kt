package com.example.fooddeliveryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fooddeliveryapp.model.remote.ApiService

class HomeVMFactory(val apiService: ApiService): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return HomeViewModel(apiService) as T
    }
}