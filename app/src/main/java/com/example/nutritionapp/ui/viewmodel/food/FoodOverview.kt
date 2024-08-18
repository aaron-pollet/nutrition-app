package com.example.nutritionapp.ui.viewmodel.food

import FoodReviewDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritionapp.model.MealType
import drawable.SearchFoodDialog

@Composable
fun FoodOverview(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel = viewModel(factory = FoodViewModel.Factory),
) {
    val uiListState by foodViewModel.uiListState.collectAsState()

    // states for searchfood and foodreview dialogs
    var showSearchFoodDialog by rememberSaveable { mutableStateOf(false) }
    var showFoodReviewDialog by rememberSaveable { mutableStateOf(false) }
    var selectedMealType by rememberSaveable { mutableStateOf(MealType.BREAKFAST) }

    var foodDescription by rememberSaveable { mutableStateOf("") }
    var calories by rememberSaveable { mutableStateOf("") }
    var grams by rememberSaveable { mutableStateOf("") }
    var carbs by rememberSaveable { mutableStateOf("") }
    var fats by rememberSaveable { mutableStateOf("") }
    var protein by rememberSaveable { mutableStateOf("") }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text("Amount consumed")
                    Text("0.0 kcal")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Burned")
                    Text("0.0 kcal")
                }

                Box(
                    modifier =
                        Modifier.size(100.dp)
                            .background(Color.LightGray, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Remaining calories: 0 kcal",
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("0.0/200.0")
                    Text("Protein")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("0.0/200.0")
                    Text("Fat")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("0.0/200.0")
                    Text("Carbs")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            MealCard(MealType.SNACK, onAddMeal = {
                showSearchFoodDialog = true
            }, uiListState = uiListState)
            Spacer(modifier = Modifier.height(8.dp))

            if (showSearchFoodDialog) {
                SearchFoodDialog(
                    foodDescription = foodDescription,
                    onFoodChangeDescription = { description -> foodDescription = description },
                    onSearch = {
                        foodViewModel.fetchFoodDetails(foodDescription) {
                                foodItem ->
                            calories = foodItem.calories.toString()
                            grams = foodItem.grams.toString()
                            carbs = foodItem.carbs.toString()
                            fats = foodItem.fats.toString()
                            protein = foodItem.protein.toString()
                        }

                        showFoodReviewDialog = true
                        showSearchFoodDialog = false
                    },
                    onDismissRequest = {
                        showSearchFoodDialog = false
                    },
                )
            }
            if (showFoodReviewDialog) {
                FoodReviewDialog(
                    onSave = {
                        foodViewModel.addFood(selectedMealType)
                        showFoodReviewDialog = false
                    },
                    onReturn = {
                        showFoodReviewDialog = false
                        showSearchFoodDialog = true
                    },
                    foodViewModel = foodViewModel,
                )
            }
        }
    }
}
