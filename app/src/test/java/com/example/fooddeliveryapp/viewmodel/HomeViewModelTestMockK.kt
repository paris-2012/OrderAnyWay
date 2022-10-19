package com.example.fooddeliveryapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fooddeliveryapp.model.remote.ApiService
import com.example.fooddeliveryapp.model.remote.response.CategoryResponse
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class HomeViewModelTestMockK {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val categoryResponseObserver = mockk<Observer<CategoryResponse>>(relaxed = true)
    private val apiService = mockk<ApiService>(relaxed = true)
    private lateinit var viewModel: HomeViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(apiService)
        Dispatchers.setMain(dispatcher)
    }

    @After

    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load courses for success scenario`() {
        runBlockingTest {
            val dummyResponse = Response.success(
                Gson().fromJson(Constants.Category_SUCCESS_RESPONSE, CategoryResponse::class.java)
            )

            coEvery { apiService.getCategoryInfo() } returns dummyResponse

            viewModel.categoryLiveData.observeForever(categoryResponseObserver)

            viewModel.getAllCategory()

            coVerify { apiService.getCategoryInfo() }

            val expectedResult =
                Gson().fromJson(
                    Constants.Category_SUCCESS_RESPONSE,
                    CategoryResponse::class.java
                )

            coVerify { categoryResponseObserver.onChanged(expectedResult) }

            viewModel.categoryLiveData.removeObserver(categoryResponseObserver)
        }
    }

}