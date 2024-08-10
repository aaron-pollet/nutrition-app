package com.example.nutritionapp.data

import com.example.nutritionapp.model.Food
import com.example.nutritionapp.network.FoodApiService
import com.example.nutritionapp.network.asDomainObject

interface FoodRepository {
    suspend fun getFood(
        appId: String,
        appKey: String,
        nutritionType: String,
        ingredient: String,
    ): Food
}

class ApiFoodRepository(private val foodApiService: FoodApiService) : FoodRepository {
    override suspend fun getFood(
        appId: String,
        appKey: String,
        nutritionType: String,
        ingredient: String,
    ): Food {
        return foodApiService.getApiNutritionalAnalysis(
            appId = appId,
            appKey = appKey,
            nutritionType = nutritionType,
            ingredient = ingredient,
        ).asDomainObject()
    }
}
