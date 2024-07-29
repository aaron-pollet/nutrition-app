package com.example.nutritionapp.viewmodel

import data.Food

data class FoodUiState(
    val foods: List<Food> = listOf(),
    val newFoodName: String = "",
    val newFoodDescription: String = "",
)
