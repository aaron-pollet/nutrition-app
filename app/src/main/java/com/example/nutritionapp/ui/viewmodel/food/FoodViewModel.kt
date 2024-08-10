package com.example.nutritionapp.ui.viewmodel.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.nutritionapp.NutritionApplication
import com.example.nutritionapp.data.FoodRepository
import com.example.nutritionapp.data.FoodSampler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {
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

    private fun getNutrtion() {
        viewModelScope.launch {
            foodApiState = FoodApiState.Loading
            try {
                val response =
                    foodRepository.getFood(
                        "cd137a78",
                        "99d47aed28a398a38117e0487cbd6813",
                        "logging",
                        "100g chicken",
                    )
                foodApiState = FoodApiState.Success(response)
                println("foods: $response")
            } catch (e: SocketTimeoutException) {
                foodApiState = FoodApiState.Error
            } catch (e: IOException) {
                foodApiState = FoodApiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = this[APPLICATION_KEY] as NutritionApplication
                    val foodRepository = application.container.foodRepository
                    FoodViewModel(foodRepository)
                }
            }
    }
}
