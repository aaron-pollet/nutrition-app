package com.example.nutritionapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExerciseViewModel : ViewModel() {
    private val _exerciseUiState = MutableStateFlow(ExerciseUiState())
    val exerciseUiState = _exerciseUiState.asStateFlow()

    init {
        Log.i("ExerciseViewModel", "creating new instance of $this")
    }
}
