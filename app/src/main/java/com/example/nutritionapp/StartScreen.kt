package com.example.nutritionapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritionapp.viewmodel.FoodViewModel

@Composable
fun StartScreen(
    addingVisible: Boolean,
    onVisibilityChanged: (Boolean) -> Unit,
) {
    val viewModel: FoodViewModel = viewModel()
    val foodUiState by viewModel.foodUiState.collectAsState()

    val foods = foodUiState.foods
    Box {
        LazyColumn {
            items(foods) {
                FoodItem(it.name, it.description)
            }
        }
    }

    if (addingVisible) {
        CreateFood(
            foodName = foodUiState.newFoodName,
            foodDescription = foodUiState.newFoodDescription,
            onFoodChangeName = { name -> viewModel.setNewFoodName(name) },
            onFoodChangeDescription = { description -> viewModel.setNewTaskDescription(description) },
            onCancel = { onVisibilityChanged(false) },
            onSave = {
                viewModel.addFood()
                onVisibilityChanged(false)
            },
        )
    }
}
