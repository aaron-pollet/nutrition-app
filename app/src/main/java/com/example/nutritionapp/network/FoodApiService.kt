package com.example.nutritionapp.network

import kotlinx.coroutines.flow.flow
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

fun FoodApiService.getFoodsAsFlow(
    appId: String,
    appKey: String,
    nutritionType: String,
    ingredient: String,
) = flow {
    emit(
        getApiNutritionalAnalysis(
            appId = appId,
            appKey = appKey,
            nutritionType = nutritionType,
            ingredient = ingredient,
        ),
    )
}
