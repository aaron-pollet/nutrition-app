package com.example.nutritionapp.ui.viewmodel.food

import android.util.Log
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
import com.example.nutritionapp.data.persistence.FoodRepository
import com.example.nutritionapp.model.Food
import com.example.nutritionapp.model.MealType
import com.example.nutritionapp.network.FoodApiService
import com.example.nutritionapp.network.asDomainObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FoodViewModel(
    private val foodRepository: FoodRepository,
    private val foodApiService: FoodApiService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(FoodUiState())
    val uiState: StateFlow<FoodUiState> = _uiState.asStateFlow()

    private val _selectedMealType = MutableStateFlow(MealType.BREAKFAST)
    val selectedMealType: StateFlow<MealType> = _selectedMealType.asStateFlow()

    fun setMealType(mealType: MealType) {
        _selectedMealType.value = mealType
    }

    var foodApiState: FoodApiState by mutableStateOf(FoodApiState.Loading)
        private set

    lateinit var uiListState: StateFlow<List<Food>>

    init {
        getRepoFood()
    }

    fun fetchFoodDetails(
        foodDescription: String,
        onResult: (Food) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    foodApiService.getApiNutritionalAnalysis(
                        "cd137a78",
                        "99d47aed28a398a38117e0487cbd6813",
                        "logging",
                        foodDescription,
                    )
                val food = response.asDomainObject(mealType = selectedMealType.value)
                _uiState.update {
                        currentState ->
                    currentState.copy(
                        newFoodDescription = food.desc,
                        newFoodCalories = food.calories,
                        newFoodGrams = food.grams,
                        newFoodCarbs = food.carbs,
                        newFoodFats = food.fats,
                        newFoodProtein = food.protein,
                    )
                }
                Log.d("API reponse successfully", response.toString())
                onResult(response.asDomainObject(mealType = selectedMealType.value))
                Log.d("response as DomainObject:", response.asDomainObject(mealType = selectedMealType.value).toString())
            } catch (e: IOException) {
                Log.e("TEST", "Error in searchFood: ${e.message}")
            }
        }
    }

    fun addFood(mealType: MealType) {
        viewModelScope.launch {
            try {
                val response =
                    foodApiService.getApiNutritionalAnalysis(
                        "cd137a78",
                        "99d47aed28a398a38117e0487cbd6813",
                        "logging",
                        _uiState.value.newFoodDescription,
                    )

                val food = response.asDomainObject(mealType)
                // Insert into the repository
                foodRepository.insert(food)
                Log.d("FoodViewModel", "Food saved: $food")

                // Update UI state after successful save
                _uiState.update { currentState ->
                    currentState.copy(
                        newFoodDescription = "",
                        newFoodCalories = "",
                        newFoodGrams = "",
                        newFoodCarbs = "",
                        newFoodFats = "",
                        newFoodProtein = "",
                        doScrollCommand = currentState.doScrollCommand.plus(1),
                    )
                }
            } catch (e: IOException) {
                Log.e("FoodViewModel", "Error saving food: ${e.message}")
            } catch (e: HttpException) {
                Log.e("FoodViewModel", "HTTP error: ${e.code()} - ${e.message()}")
            }
        }
    }

    fun getFoodByMealType(mealType: MealType): Flow<List<Food>> {
        return foodRepository.getAllItems().map { foods ->
            foods.filter { it.mealType == mealType }
        }
    }

    private fun getRepoFood() {
        try {
            viewModelScope.launch { foodRepository.refresh() }
            uiListState =
                foodRepository.getAllItems().stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = listOf(),
                )
            foodApiState = FoodApiState.Success
        } catch (e: IOException) {
            foodApiState = FoodApiState.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val application = this[APPLICATION_KEY] as NutritionApplication
                    val foodRepository = application.container.foodRepository
                    val foodApiService = application.container.foodApiService
                    FoodViewModel(foodRepository, foodApiService)
                }
            }
    }
}
