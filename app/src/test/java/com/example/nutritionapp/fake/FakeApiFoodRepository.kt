package com.example.nutritionapp.fake

import com.example.nutritionapp.data.persistence.FoodRepository
import com.example.nutritionapp.model.Food
import kotlinx.coroutines.flow.Flow

class FakeApiFoodRepository : FoodRepository {
    override suspend fun insert(item: Food) {
        TODO("Not yet implemented")
    }

    override fun getAllItems(): Flow<List<Food>> {
        TODO("Not yet implemented")
    }

    override fun getItem(id: Int): Flow<Food> {
        TODO("Not yet implemented")
    }

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }
}
