package com.example.nutritionapp.ui.viewmodel.food

import com.example.nutritionapp.data.FoodSampler
import com.example.nutritionapp.model.Food

data class FoodUiState(
    val foods: List<FoodSampler> = listOf(),
    val newFoodName: String = "",
    val newFoodDescription: String = "",
    val doScrollCommand: Int = 0,
    val scrollToIndex: Int = 0,
)

sealed interface FoodApiState {
    data class Success(val data: Food) : FoodApiState

    object Error : FoodApiState

    object Loading : FoodApiState
}
