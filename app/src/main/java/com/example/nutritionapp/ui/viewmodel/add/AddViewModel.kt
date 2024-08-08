package com.example.nutritionapp.ui.viewmodel.add

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddViewModel : ViewModel() {
    private val _addUiState = MutableStateFlow(AddUiState())
    val addUiState = _addUiState.asStateFlow()

    init {
        Log.i("AddViewModel", "creating new instance of $this")
    }
}
