import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.nutritionapp.R
import com.example.nutritionapp.ui.viewmodel.food.FoodViewModel

@Composable
fun FoodReviewDialog(
    onSave: () -> Unit,
    onReturn: () -> Unit,
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel,
) {
    val uiState by foodViewModel.uiState.collectAsState()

    Dialog(onDismissRequest = onReturn) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(dimensionResource(R.dimen.medium_corner_radius)),
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.review_food),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Display the nutritional information
                Text(text = "Description: ${uiState.newFoodDescription}")
                Text(text = "Total Grams: ${uiState.newFoodGrams}")
                Text(text = "Calories: ${uiState.newFoodCalories} kcal")
                Text(text = "Carbs: ${uiState.newFoodCarbs} g")
                Text(text = "Fats: ${uiState.newFoodFats} g")
                Text(text = "Protein: ${uiState.newFoodProtein} g")

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons to save or return
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(onClick = onReturn) {
                        Text("Return")
                    }

                    TextButton(onClick = onSave) {
                        Text("Save")
                    }
                }
            }
        }
    }
}
