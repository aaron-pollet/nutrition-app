package com.example.nutritionapp.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyBottomAppBar(
    goHome: () -> Unit,
    goToExercise: () -> Unit,
    goToAdd: () -> Unit,
    goToWeight: () -> Unit,
    goToProfile: () -> Unit,
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            IconButton(onClick = goHome) {
                Icon(Icons.Filled.Home, contentDescription = "navigate to home screen")
            }
            IconButton(onClick = goToExercise) {
                Icon(Icons.Filled.Build, contentDescription = "navigate to exercise screen")
            }
            IconButton(onClick = goToAdd) {
                Icon(Icons.Filled.AddCircle, contentDescription = "navigate to add screen")
            }
            IconButton(onClick = goToWeight) {
                Icon(Icons.Filled.AccountBox, contentDescription = "navigate to weight screen")
            }
            IconButton(onClick = goToProfile) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "navigate to profile screen",
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPreview() {
    MyBottomAppBar(goHome = {}, goToExercise = {}, goToAdd = {}, goToWeight = {}, goToProfile = {})
}
