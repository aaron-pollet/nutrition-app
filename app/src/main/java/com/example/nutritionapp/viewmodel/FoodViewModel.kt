package com.example.nutritionapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import data.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FoodViewModel : ViewModel() {
    private val _foodUiState = MutableStateFlow(FoodUiState(foods = Food.getAll()))
    val foodUiState = _foodUiState.asStateFlow()

    init {
        Log.i("FoodViewModel", "creating new instance of $this")
    }

    fun addFood() {
        _foodUiState.update {
                currentState ->
            currentState.copy(
                currentState.foods + Food(currentState.newFoodName, currentState.newFoodDescription),
                newFoodName = "",
                newFoodDescription = "",
                doScrollCommand = currentState.doScrollCommand.plus(1),
                scrollToIndex = currentState.foods.size,
            )
        }
    }

    fun setNewFoodName(name: String) {
        _foodUiState.update {
            it.copy(newFoodName = name)
        }
    }

    fun setNewTaskDescription(description: String) {
        _foodUiState.update { it.copy(newFoodDescription = description) }
    }
}
