package drawable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.nutritionapp.R

@Composable
fun CreateFood(
    foodName: String,
    foodDescription: String,
    onFoodChangeName: (String) -> Unit,
    onFoodChangeDescription: (String) -> Unit,
    onSave: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(modifier = modifier, shape = RoundedCornerShape(dimensionResource(R.dimen.medium_corner_radius))) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier =
                    Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
            ) {
                Text(stringResource(R.string.create_food_add), style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = modifier.padding(16.dp))
                OutlinedTextField(label = { Text("Name:") }, value = foodName, onValueChange = onFoodChangeName)
                OutlinedTextField(label = { Text("Description:") }, value = foodDescription, onValueChange = onFoodChangeDescription)
                Spacer(modifier = Modifier.padding(8.dp))
                TextButton(onClick = onSave, Modifier.align(Alignment.End)) { Text("save") }
            }
        }
    }
}

// @Preview
// @Composable
// fun CreateFoodPreview() {
//    CreateFood("pizza", "delicious", {}, {}, {}, {})
// }
