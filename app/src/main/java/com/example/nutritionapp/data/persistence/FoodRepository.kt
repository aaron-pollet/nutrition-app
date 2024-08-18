package com.example.nutritionapp.data.persistence

import android.util.Log
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.nutritionapp.model.Food
import com.example.nutritionapp.network.FoodApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface FoodRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Food)

    fun getAllItems(): Flow<List<Food>>

    fun getItem(id: Int): Flow<Food>

    suspend fun refresh()
}

class CachingFoodRepository(
    private val foodDao: FoodDao,
    private val foodApiService: FoodApiService,
) : FoodRepository {
    override suspend fun insert(item: Food) {
        foodDao.insert(item.asDbFood())
    }

    override fun getAllItems(): Flow<List<Food>> {
        return foodDao.getAllItems().map {
            it.asDomainFoods()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getItem(id: Int): Flow<Food> {
        return foodDao.getItem(id).map {
            it.asDomainFood()
        }
    }

    override suspend fun refresh() {
        try {
//            foodApiService.getFoodsAsFlow(
//                appId = "cd137a78",
//                appKey = "99d47aed28a398a38117e0487cbd6813",
//                nutritionType = "logging",
//                ingredient = "",
//            ).collect {
//                it.asDomainObject().let { food ->
//                    Log.i("TEST", "refresh: $food")
//                    insert(food)
//                }
//            }
        } catch (e: Exception) {
            Log.e("TEST", "Error in refresh: ${e.message}")
        }
    }
}
