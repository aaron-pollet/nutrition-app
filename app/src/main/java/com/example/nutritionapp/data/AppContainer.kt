package com.example.nutritionapp.data

import com.example.nutritionapp.data.DefaultAppContainer.RetrofitClient.retrofitService
import com.example.nutritionapp.network.FoodApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val foodRepository: FoodRepository
}

class DefaultAppContainer() : AppContainer {
    object RetrofitClient {
        private const val BASE_URL = "https://api.edamam.com"
        private val json =
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        private val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

        val retrofitService: FoodApiService by lazy {
            retrofit.create(FoodApiService::class.java)
        }
    }

    override val foodRepository: FoodRepository by lazy {
        ApiFoodRepository(retrofitService)
    }
}
