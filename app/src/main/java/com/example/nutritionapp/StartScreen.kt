package com.example.nutritionapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import data.Food

@Composable
fun StartScreen(
    foods: SnapshotStateList<Food>,
    addingVisible: Boolean,
    stopAdding: () -> Unit,
) {
    Box {
        Column {
            for (item in foods) {
                FoodItem(item.name, item.description)
            }
        }
    }
    var newFoodName by remember { mutableStateOf("") }
    var newFoodDescription by remember { mutableStateOf("") }

    if (addingVisible) {
        CreateFood(
            foodName = newFoodName,
            foodDescription = newFoodDescription,
            onFoodChangeName = { newFoodName = it },
            onFoodChangeDescription = { newFoodDescription = it },
            onCancel = { stopAdding() },
            onSave = {
                foods.add(Food(newFoodName, newFoodDescription))
                stopAdding()
                newFoodName = ""
                newFoodDescription = ""
            },
        )
    }
}
