package com.example.nutritionapp.ui.viewmodel.food

data class FoodUiState(
    val newFoodDescription: String = "",
    val newFoodCalories: String = "",
    val newFoodGrams: String = "",
    val newFoodCarbs: String = "",
    val newFoodFats: String = "",
    val newFoodProtein: String = "",
    val doScrollCommand: Int = 0,
    val scrollToIndex: Int = 0,
)

sealed interface FoodApiState {
    object Success : FoodApiState

    object Error : FoodApiState

    object Loading : FoodApiState
}
