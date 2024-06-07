package com.example.nutritionapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutritionapp.ui.theme.NutritionAppTheme
import data.Food

enum class Destinations {
    Start,
    Exercise,
    Add,
    Weight,
    Profile,
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodApp() {
    var addingVisible by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            MyTopAppBar(
                {
                    val isStartDestination = currentBackStackEntry?.destination?.route == Destinations.Start.name
                    if (isStartDestination) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    } else {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                when (currentBackStackEntry?.destination?.route) {
                    Destinations.Exercise.name -> R.string.app_exercise_title
                    Destinations.Add.name -> R.string.app_add_new_item_title
                    Destinations.Weight.name -> R.string.app_weight_tracking_title
                    Destinations.Profile.name -> R.string.app_profile_title
                    else -> R.string.app_title
                },
            )
        },
        bottomBar = {
            MyBottomAppBar(
                { navController.popBackStack(Destinations.Start.name, false) },
                { navController.navigate(Destinations.Exercise.name) },
                { navController.navigate(Destinations.Add.name) },
                { navController.navigate(Destinations.Weight.name) },
                { navController.navigate(Destinations.Profile.name) },
            )
        },
        floatingActionButton = {
            when (currentBackStackEntry?.destination?.route) {
                Destinations.Start.name -> {
                    FloatingActionButton(onClick = { addingVisible = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
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
        // added state, but didn't use remember, this will trigger on each recompose
        // more on lists and viewmodels will follow later!
        val foods = remember { Food.getAll().toMutableStateList() }

        NavHost(
            navController = navController,
            startDestination = Destinations.Start.name,
            Modifier.padding(innerPadding),
        ) {
            composable(Destinations.Start.name) {
                StartScreen(foods, addingVisible, { addingVisible = false })
            }
            composable(Destinations.Exercise.name) {
                Text("Exercise screen")
            }
            composable(Destinations.Add.name) {
                Text("Add screen")
            }
            composable(Destinations.Weight.name) {
                Text("Weight tracking screen")
            }
            composable(Destinations.Profile.name) {
                Text("Profile screen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodAppPreview() {
    NutritionAppTheme {
        val image = painterResource(R.drawable.backgroundimage)
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alpha = 0.4F,
            )
            FoodApp()
        }
    }
}
