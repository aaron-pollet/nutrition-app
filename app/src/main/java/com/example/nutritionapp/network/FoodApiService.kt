package com.example.nutritionapp.network

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
