// package com.example.nutritionapp.ui.viewmodel.exercise
//
// import androidx.compose.runtime.getValue
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.setValue
// import androidx.lifecycle.ViewModel
// import androidx.lifecycle.ViewModelProvider
// import androidx.lifecycle.viewModelScope
// import androidx.lifecycle.viewmodel.initializer
// import androidx.lifecycle.viewmodel.viewModelFactory
// import com.example.nutritionapp.NutritionApplication
// import com.example.nutritionapp.data.ExerciseRepository
// import com.example.nutritionapp.ui.viewmodel.food.FoodApiState
// import kotlinx.coroutines.flow.MutableStateFlow
// import kotlinx.coroutines.flow.StateFlow
// import kotlinx.coroutines.flow.asStateFlow
// import kotlinx.coroutines.flow.update
// import kotlinx.coroutines.launch
// import java.io.IOException
// import java.net.SocketTimeoutException
//
// class ExerciseViewModel(private val exerciseRepository: ExerciseRepository) : ViewModel() {
//    private val _uiState = MutableStateFlow(ExerciseUiState())
//    val uiState: StateFlow<ExerciseUiState> = _uiState.asStateFlow()
//
//    var exerciseApiState: ExcerciseApiState by mutableStateOf(ExerciseApiState.Loading)
//        private set
//
//    init {
//        getExcercises()
//    }
//
//    fun addExcercise() {
//        _uiState.update { currentState ->
//            currentState.copy(
//                currentState.foods +
//                    ExerciseSampler(
//                        currentState.newExerciseName,
//                        currentState.newExerciseDescription,
//                    ),
//                newExcersiceName = "",
//                newExcersiceDescription = "",
//                doScrollCommand = currentState.doScrollCommand.plus(1),
//                scrollToIndex = currentState.foods.size,
//            )
//        }
//    }
//
//    fun setNewExcersiceName(name: String) {
//        _uiState.update {
//            it.copy(newFoodName = name)
//        }
//    }
//
//    fun setNewExcersiceDescription(description: String) {
//        _uiState.update { it.copy(newExcersiceDescription = description) }
//    }
//
//    private fun getExercises() {
//        viewModelScope.launch {
//            exerciseApiState = ExerciseApiState.Loading
//            try {
//                val response =
//                    exerciseRepository.getExcercises()
//                exerciseApiState = FoodApiState.Success(response)
//                println("foods: $response")
//            } catch (e: SocketTimeoutException) {
//                exerciseApiState = FoodApiState.Error
//            } catch (e: IOException) {
//                exerciseApiState = FoodApiState.Error
//            }
//        }
//    }
//
//    companion object {
//        val Factory: ViewModelProvider.Factory =
//            viewModelFactory {
//                initializer {
//                    val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NutritionApplication
//                    val excersiceRepository = application.container.exerciseRepository
//                    ExcerciseViewModel(exerciseRepository)
//                }
//            }
//    }
// }
