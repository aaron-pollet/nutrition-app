package com.example.nutritionapp.ui.viewmodel.food

import com.example.nutritionapp.data.FoodSampler

data class FoodUiState(
    val foods: List<FoodSampler> = listOf(),
    val newFoodName: String = "",
    val newFoodDescription: String = "",
    val doScrollCommand: Int = 0,
    val scrollToIndex: Int = 0,
)
