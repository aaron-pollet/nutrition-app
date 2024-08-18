package com.example.nutritionapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutritionapp.ui.NutritionAppOverviewScreen
import com.example.nutritionapp.ui.navigation.MyBottomAppBar
import com.example.nutritionapp.ui.navigation.MyTopAppBar
import com.example.nutritionapp.ui.theme.NutritionAppTheme
import com.example.nutritionapp.ui.viewmodel.food.FoodOverview

enum class Destinations {
    Start,
    Exercise,
    Add,
    Weight,
    Profile,
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun FoodApp(navController: NavHostController = rememberNavController()) {
    var addingVisible by rememberSaveable { mutableStateOf(false) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val canNavigateBack = navController.previousBackStackEntry != null
    val navigateUp: () -> Unit = { navController.navigateUp() }
    val goHome: () -> Unit = {
        navController.popBackStack(
            NutritionAppOverviewScreen.Start.name,
            inclusive = false,
        )
    }
    val goToProfile = { navController.navigate(NutritionAppOverviewScreen.Profile.name) }
    val goToWeight = { navController.navigate(NutritionAppOverviewScreen.Weight.name) }
    val goToExercise = { navController.navigate(NutritionAppOverviewScreen.Exercise.name) }
    val goToAdd = { navController.navigate(NutritionAppOverviewScreen.Add.name) }

    val currentScreenTitle =
        NutritionAppOverviewScreen.valueOf(
            currentBackStackEntry?.destination?.route ?: NutritionAppOverviewScreen.Start.name,
        ).title

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            MyTopAppBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                currentScreenTitle = currentScreenTitle,
            )
        },
        bottomBar = {
            MyBottomAppBar(
                goHome,
                goToExercise,
                goToAdd,
                goToWeight,
                goToProfile,
            )
        },
        floatingActionButton = {
            when (currentBackStackEntry?.destination?.route) {
//                Destinations.Start.name -> {
//                    FloatingActionButton(onClick = { addingVisible = true }) {
//                        Icon(Icons.Default.Add, contentDescription = "Add")
//                    }
//                }
                Destinations.Exercise.name -> {
                    FloatingActionButton(onClick = { addingVisible = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add exercise")
                    }
                }
                Destinations.Weight.name -> {
                    FloatingActionButton(onClick = { addingVisible = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add weight")
                    }
                }
            }
        },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = Destinations.Start.name) {
                FoodOverview()
            }
            composable(route = Destinations.Exercise.name) {
                Text("Exercise screen")
            }
            composable(route = Destinations.Add.name) {
                Text("Add screen")
            }
            composable(route = Destinations.Weight.name) {
                Text("Weight tracking screen")
            }
            composable(route = Destinations.Profile.name) {
                Text("Profile screen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodAppPreview() {
    NutritionAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            FoodApp()
        }
    }
}
