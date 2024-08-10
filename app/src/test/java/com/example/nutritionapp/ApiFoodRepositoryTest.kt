package com.example.nutritionapp

import com.example.nutritionapp.data.ApiFoodRepository
import com.example.nutritionapp.fake.FakeDataSource
import com.example.nutritionapp.fake.FakeFoodsApiService
import com.example.nutritionapp.network.asDomainObject
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiFoodRepositoryTest {
    @Test
    fun apiFoodRepository_getFood_verifyFood() =
        runTest {
            val repository = ApiFoodRepository(FakeFoodsApiService())

            assertEquals(
                FakeDataSource.fakeApiFood.asDomainObject(),
                repository.getFood(
                    "appId",
                    "appKey",
                    "nutritionType",
                    "ingredient",
                ),
            )
        }
}
