package com.example.nutritionapp.ui.viewmodel.food

import com.example.nutritionapp.model.Food

sealed interface FoodApiState {
    data class Success(val data: Food) : FoodApiState

    object Error : FoodApiState

    object Loading : FoodApiState
}
