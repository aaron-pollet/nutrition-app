package com.example.nutritionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.nutritionapp.ui.theme.NutritionAppTheme
import org.jetbrains.annotations.VisibleForTesting
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutritionAppTheme {
                val image = painterResource(R.drawable.backgroundimage)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        // alpha = 0.4F
                    )
                    FoodApp()
                }
            }
        }
    }

    @VisibleForTesting
    internal fun calculateTip(
        amount: Double,
        tipPercent: Double = 15.0,
        roundUp: Boolean,
    ): String {
        var tip = tipPercent / 100 * amount
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        return NumberFormat.getCurrencyInstance().format(tip)
    }
}
