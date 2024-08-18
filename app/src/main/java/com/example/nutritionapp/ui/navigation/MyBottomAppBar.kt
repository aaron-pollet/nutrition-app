package com.example.nutritionapp.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutritionapp.R

@Composable
fun MyBottomAppBar(
    goHome: () -> Unit,
    goToExercise: () -> Unit,
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
                Icon(Icons.Filled.Home, contentDescription = "navigate to home screen", modifier = Modifier.size(24.dp))
            }
            IconButton(onClick = goToExercise) {
                val painter: Painter = painterResource(id = R.drawable.barbell_12635735)
                Icon(painter, contentDescription = "navigate to exercise screen", modifier = Modifier.size(24.dp))
            }
            IconButton(onClick = goToWeight) {
                val painter: Painter = painterResource(id = R.drawable.weightscale_11798705)
                Icon(painter, contentDescription = "navigate to weight screen", modifier = Modifier.size(24.dp))
            }
            IconButton(onClick = goToProfile) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "navigate to profile screen",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPreview() {
    MyBottomAppBar(goHome = {}, goToExercise = {}, goToWeight = {}, goToProfile = {})
}
