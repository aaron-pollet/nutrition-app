package com.example.nutritionapp.data

import android.content.Context
import androidx.room.Room
import com.example.nutritionapp.data.persistence.CachingFoodRepository
import com.example.nutritionapp.data.persistence.FoodDao
import com.example.nutritionapp.data.persistence.FoodDatabase
import com.example.nutritionapp.data.persistence.FoodRepository
import com.example.nutritionapp.network.FoodApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val foodRepository: FoodRepository
    val foodApiService: FoodApiService
}

class DefaultAppContainer(
    private val applicationContext: Context,
) : AppContainer {
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

    private val foodDb: FoodDatabase by lazy {
        Room.databaseBuilder(applicationContext, FoodDatabase::class.java, "food-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    private val foodDao: FoodDao by lazy {
        foodDb.foodDao()
    }

    override val foodRepository: FoodRepository by lazy {
        CachingFoodRepository(
            foodDao = foodDao,
            foodApiService = RetrofitClient.retrofitService,
        )
    }
    override val foodApiService: FoodApiService by lazy {
        RetrofitClient.retrofitService
    }
}
