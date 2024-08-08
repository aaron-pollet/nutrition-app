package com.example.nutritionapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {
    @GET("/api/nutrition-data")
    suspend fun getApiNutritionalAnalysis(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        // cooking / logging
        @Query("nutrition-type") nutritionType: String,
        @Query("ingr") ingredient: String,
    ): ApiFood
}

// TODO test met postman om te zien hoe de API werkt
// deze url werkt
// https://api.edamam.com/api/nutrition-data?
// app_id=cd137a78&
// app_key=99d47aed28a398a38117e0487cbd6813&
// nutrition-type=logging&
// ingr=100g%20chicken%2C%20500g%20rice

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

    val foodApiService: FoodApiService = retrofit.create(FoodApiService::class.java)
}

// private var retrofit =
//    Retrofit.Builder()
//        .addConverterFactory(
//            Json.asConverterFactory(contentType = "application/json".toMediaType()),
//        )
//        // .baseUrl("http://10.0.2.2:3000")
//        .baseUrl("https://api.edamam.com")
//        .build()

// object FoodApi {
//    val foodService: FoodApiService by lazy {
//        retrofit.create(FoodApiService::class.java)
//    }
// }
