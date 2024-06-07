package com.example.nutritionapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyBottomAppBar(onHome:() -> Unit, onExercise:() -> Unit, onAdd:() -> Unit, onWeight:() -> Unit, onProfile:() -> Unit) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            IconButton(onClick = onHome) {
                Icon(Icons.Filled.Home, contentDescription = "Localized description")
            }
            IconButton(onClick = onExercise) {
                Icon(Icons.Filled.Build, contentDescription = "Localized description")
            }
            IconButton(onClick = onAdd) {
                Icon(Icons.Filled.AddCircle, contentDescription = "Localized description")
            }
            IconButton(onClick = onWeight) {
                Icon(Icons.Filled.AccountBox, contentDescription = "Localized description")
            }
            IconButton(onClick = onProfile) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                )
            }
        },
    )
}