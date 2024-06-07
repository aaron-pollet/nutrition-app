package com.example.nutritionapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateFood(
    foodName: String,
    foodDescription: String,
    onFoodChangeName: (String) -> Unit,
    onFoodChangeDescription: (String) -> Unit,
    onCancel: () -> Unit,
    onSave: () -> Unit,
) {
    Column {
        TextField(label = { Text("Name:") }, value = foodName, onValueChange = onFoodChangeName)
        TextField(label = { Text("Description:") }, value = foodDescription, onValueChange = onFoodChangeDescription)
        Button(onClick = onCancel) { Text("cancel") }
        Button(onClick = onSave) { Text("save") }
    }
}

@Preview
@Composable
fun createFoodPreview() {
    CreateFood("pizza","delicious", {}, {}, {}, {})
}
