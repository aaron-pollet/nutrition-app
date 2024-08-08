package com.example.nutritionapp.ui.viewmodel.food

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutritionapp.R

@Composable
fun FoodOverview(
    addingVisible: Boolean,
    onVisibilityChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel = viewModel(),
) {
    val foodUiState by foodViewModel.uiState.collectAsState()

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text("Amount eaten")
                Text("0.0 kcal")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Burned")
                Text("0.0 kcal")
            }

            Box(
                modifier =
                    Modifier
                        .size(100.dp)
                        .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                // Text composable with centered text
                Text(text = "Remaining calories", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
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
                Text("Proteins")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("0.0/200.0")
                Text("Fats")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("0.0/200.0")
                Text("Carbs")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = modifier.fillMaxWidth()) {
//            MealCard("Breakfast")
//            Spacer(modifier = Modifier.height(8.dp))
//            MealCard("Lunch")
//            Spacer(modifier = Modifier.height(8.dp))
//            MealCard("Dinner")
//            Spacer(modifier = Modifier.height(8.dp))
//            MealCard("Snack")
//            Spacer(modifier = Modifier.height(8.dp))
            Box {
                val lazyListState = rememberLazyListState()
                LazyColumn(state = lazyListState) {
                    val foodApiState = foodViewModel.foodApiState
                    when (foodApiState) {
                        is FoodApiState.Loading -> item { Text("Loading...") }
                        is FoodApiState.Error -> item { Text("Couldn't load...") }
                        is FoodApiState.Success ->
                            item {
                                FoodItem(
                                    foodApiState.data.desc,
                                    foodApiState.data.calories,
                                    foodApiState.data.calories,
                                    foodApiState.data.carbs,
                                    foodApiState.data.fats,
                                    foodApiState.data.protein,
                                )
                            }
                    }
                }
            }
//                val coroutineScope = rememberCoroutineScope()
//                // bij toevoegen van een nieuwe item gaat vanzelf gescrollt worden naar het nieuwe item
//                LaunchedEffect(foodUiState.scrollToActionIdx != 0) {
//                    coroutineScope.launch {
//                        listState.scrollToItem(foodUiState.scrollToActionIdx)
//                    }
//                }
        }
    }
}

//    if (addingVisible) {
//        CreateFood(
//            foodDescription = foodUiState.newFoodDescription,
//            foodGrams = foodUiState.newFoodDescription,
//            onFoodChangeName = { name -> foodViewModel.setNewFoodName(name) },
//            onFoodChangeDescription = { description -> foodViewModel.setNewTaskDescription(description) },
//            onSave = {
//                foodViewModel.addFood()
//                onVisibilityChanged(false)
//            },
//            onDismissRequest = {
//                // TODO clear viewmodel text
//                onVisibilityChanged(false)
//            },
//        )
//    }
// }

@Composable
fun MealCard(name: String) {
    ElevatedCard(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(60.dp),
    ) {
        val expanded by rememberSaveable { mutableStateOf(false) }
//        val color by animateColorAsState(
//            targetValue =
//                if (expanded) {
//                    MaterialTheme.colorScheme.tertiaryContainer
//                } else {
//                    MaterialTheme.colorScheme.secondaryContainer
//                },
//            label = "colorAnimation",
//        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .animateContentSize(
                        animationSpec =
                            spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessMedium,
                            ),
                    )
                    .height(IntrinsicSize.Min)
                    .padding(16.dp)
                    .fillMaxWidth(),
//                    .background(color)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier =
                        Modifier
                            .size(40.dp)
                            .background(Color.LightGray, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your placeholder image resource
                        contentDescription = "add $name",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(30.dp),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(name)
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { expanded != expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = "expand",
                    modifier = Modifier.size(24.dp),
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add $name",
                    modifier = Modifier.size(24.dp),
                )
            }
            if (expanded) {
                Text(
                    "Expanded contentdescription, what has been chosen in foods",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodOverviewPreview() {
    FoodOverview(addingVisible = false, onVisibilityChanged = {})
}
