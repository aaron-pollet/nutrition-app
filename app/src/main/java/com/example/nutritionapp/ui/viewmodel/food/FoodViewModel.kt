package com.example.nutritionapp.ui.viewmodel.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionapp.data.FoodSampler
import com.example.nutritionapp.network.RetrofitClient
import com.example.nutritionapp.network.asDomainObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class FoodViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FoodUiState(foods = FoodSampler.getAll()))
    val uiState: StateFlow<FoodUiState> = _uiState.asStateFlow()

    var foodApiState: FoodApiState by mutableStateOf(FoodApiState.Loading)
        private set

    init {
        getNutrtion()
    }

    fun addFood() {
        _uiState.update { currentState ->
            currentState.copy(
                currentState.foods +
                    FoodSampler(
                        currentState.newFoodName,
                        currentState.newFoodDescription,
                    ),
                newFoodName = "",
                newFoodDescription = "",
                doScrollCommand = currentState.doScrollCommand.plus(1),
                scrollToIndex = currentState.foods.size,
            )
        }
    }

    fun setNewFoodName(name: String) {
        _uiState.update {
            it.copy(newFoodName = name)
        }
    }

    fun setNewTaskDescription(description: String) {
        _uiState.update { it.copy(newFoodDescription = description) }
    }

    //    private fun getApiFoods() {
//        val appId = "cd137a78"
//        val appKey = "99d47aed28a398a38117e0487cbd6813"
//        val nutritionType = "logging" // or "logging"
//        val ingredient = "100g chicken"
//        try {
//            viewModelScope.launch {
//                val result = foodService.getNutritionData(appId, appKey, nutritionType, ingredient)
//                println("foods: $result")
//            }
//        } catch (e: Exception) {
//            // throw an appropriate exception
//            println("Error: $e")
//        }
//        viewModelScope.launch {
//            val result = foodService.getNutritionData(appId, appKey, nutritionType, ingredient)
//            println("foods: $result")
//        }
    private fun getNutrtion() {
        viewModelScope.launch {
            foodApiState = FoodApiState.Loading
            try {
                val response =
                    RetrofitClient.foodApiService.getApiNutritionalAnalysis(
                        "cd137a78",
                        "99d47aed28a398a38117e0487cbd6813",
                        "logging",
                        "100g chicken",
                    )
                foodApiState = FoodApiState.Success(response.asDomainObject())
                println("foods: $response")
            } catch (e: IOException) {
                foodApiState = FoodApiState.Error
                println("Error: $e")
            }
        }
    }
}
