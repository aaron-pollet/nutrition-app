package com.example.nutritionapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WeightViewModel : ViewModel() {
    private val _weightUiState = MutableStateFlow(WeightUiState())
    val weightUiState = _weightUiState.asStateFlow()

    init {
        Log.i("WeightViewModel", "creating new instance of $this")
    }
}
