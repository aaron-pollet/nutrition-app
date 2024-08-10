package com.example.nutritionapp.fake

import com.example.nutritionapp.network.ApiFood
import com.example.nutritionapp.network.FoodApiService

class FakeFoodsApiService : FoodApiService {
    override suspend fun getApiNutritionalAnalysis(
        appId: String,
        appKey: String,
        nutritionType: String,
        ingredient: String,
    ): ApiFood {
        return FakeDataSource.fakeApiFood
    }
}
