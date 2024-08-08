package com.example.nutritionapp.ui.viewmodel.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoodItem(
    desc: String,
    calories: String,
    grams: String,
    carbs: String,
    fats: String,
    protein: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(desc, fontSize = 24.sp)
            Text(calories, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            Text(grams, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            Text(carbs, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            Text(fats, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
            Text(protein, fontSize = 16.sp, fontFamily = FontFamily.SansSerif)
        }
        var checked by rememberSaveable { mutableStateOf(false) }
        Checkbox(checked = checked, onCheckedChange = { checked = !checked })
    }
}
