package com.example.fooddeliveryapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fooddeliveryapp.model.remote.ApiService
import com.example.fooddeliveryapp.model.remote.response.CategoryResponse
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class HomeViewModelTestMockito {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var categoryResponseObserver: Observer<CategoryResponse>

    @Mock
    lateinit var apiService: ApiService

    lateinit var viewModel: HomeViewModel


    @Before
    fun setUp() {
        viewModel = HomeViewModel(apiService)
    }

    @Test
    fun `load categories for success scenario`() {
        runBlocking {
            val dummyResponse = Response.success(
                Gson().fromJson(Constants.Category_SUCCESS_RESPONSE, CategoryResponse::class.java)
            )

            doReturn(dummyResponse).`when`(apiService).getCategoryInfo()

            viewModel.categoryLiveData.observeForever(categoryResponseObserver)
            viewModel.getAllCategory()

            verify(apiService).getCategoryInfo()

            val expectedResult =
                Gson().fromJson(
                    Constants.Category_SUCCESS_RESPONSE,
                    CategoryResponse::class.java
                )

            verify(categoryResponseObserver).onChanged(expectedResult)

            viewModel.categoryLiveData.removeObserver(categoryResponseObserver)
        }
    }
}