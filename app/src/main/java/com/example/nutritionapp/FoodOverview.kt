package com.example.nutritionapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritionapp.viewmodel.FoodViewModel

@Composable
fun FoodOverview(
    addingVisible: Boolean,
    onVisibilityChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel = viewModel(),
) {
    val foodUiState by foodViewModel.foodUiState.collectAsState()
    Box(modifier = modifier) {
        LazyColumn {
            items(foodUiState.foods) {
                FoodItem(it.name, it.description)
            }
        }
    }

    if (addingVisible) {
        CreateFood(
            foodName = foodUiState.newFoodName,
            foodDescription = foodUiState.newFoodDescription,
            onFoodChangeName = { name -> foodViewModel.setNewFoodName(name) },
            onFoodChangeDescription = { description -> foodViewModel.setNewTaskDescription(description) },
            onCancel = { onVisibilityChanged(false) },
            onSave = {
                foodViewModel.addFood()
                onVisibilityChanged(false)
            },
        )
    }
}
