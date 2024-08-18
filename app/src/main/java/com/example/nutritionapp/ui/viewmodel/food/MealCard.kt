package com.example.nutritionapp.ui.viewmodel.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nutritionapp.model.Food
import com.example.nutritionapp.model.MealType

@Composable
fun MealCard(
    mealType: MealType,
    onAddMeal: () -> Unit,
    uiListState: List<Food>,
) {
//    val expanded by rememberSaveable { mutableStateOf(false) }

    ElevatedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = mealType.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onAddMeal) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add food",
                    )
                }
            }
            if (uiListState.isEmpty()) {
                Text(
                    text = "No entries",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp),
                )
            } else {
                LazyColumn {
                    items(uiListState) { foodItem ->
                        FoodItem(
                            desc = foodItem.desc,
                            calories = foodItem.calories,
                            // grams = foodItem.grams,
//                            carbs = foodItem.carbs,
//                            fats = foodItem.fats,
//                            protein = foodItem.protein,
                        )
                    }
                }
            }
        }
    }
}
