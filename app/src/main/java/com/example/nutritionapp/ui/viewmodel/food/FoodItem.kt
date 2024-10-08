package com.example.nutritionapp.ui.viewmodel.food

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodItem(
    desc: String,
    calories: Double,
) {
    Row(
        modifier =
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
    ) {
        Text(desc)
        Spacer(modifier = Modifier.weight(0.5f))
        Text("$calories kcal")
    }
}
