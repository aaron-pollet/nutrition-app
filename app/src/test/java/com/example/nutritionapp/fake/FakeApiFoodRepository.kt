package com.example.nutritionapp.fake

import com.example.nutritionapp.data.FoodRepository
import com.example.nutritionapp.model.Food
import com.example.nutritionapp.network.asDomainObject

class FakeApiFoodRepository : FoodRepository {
    override suspend fun getFood(
        appId: String,
        appKey: String,
        nutritionType: String,
        ingredient: String,
    ): Food {
        return FakeDataSource.fakeApiFood.asDomainObject()
    }
}
