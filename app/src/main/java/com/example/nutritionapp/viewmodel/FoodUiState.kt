package com.example.nutritionapp.viewmodel

import data.Food

data class FoodUiState(
    val foods: List<Food> = listOf(),
    val newFoodName: String = "",
    val newFoodDescription: String = "",
    val doScrollCommand: Int = 0,
    val scrollToIndex: Int = 0,
)
