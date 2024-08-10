package com.example.nutritionapp.network

import com.example.nutritionapp.model.Food
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiFood(
    @SerialName("uri")
    val uri: String?,
    @SerialName("calories")
    val calories: Double,
    @SerialName("totalWeight")
    val totalWeight: Double,
    @SerialName("totalNutrients")
    val totalNutrients: Map<String, Nutrient>,
    @SerialName("totalDaily")
    val totalDaily: Map<String, Nutrient>,
    @SerialName("ingredients")
    val ingredients: List<Ingredient>,
    @SerialName("totalNutrientsKCal")
    val totalNutrientsKCal: Map<String, NutrientKCal>,
)

@Serializable
data class Nutrient(
    val label: String,
    val quantity: Double,
    val unit: String,
)

@Serializable
data class Ingredient(
    val text: String,
    val parsed: List<ParsedIngredient>,
)

@Serializable
data class ParsedIngredient(
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("measure")
    val measure: String,
    @SerialName("food")
    val food: String,
    @SerialName("foodId")
    val foodId: String,
    @SerialName("weight")
    val weight: Double,
    @SerialName("nutrients")
    val nutrients: Map<String, Nutrient>,
)

@Serializable
data class NutrientKCal(
    @SerialName("label")
    val label: String,
    @SerialName("quantity")
    val quantity: Double,
    @SerialName("unit")
    val unit: String,
)

fun ApiFood.asDomainObject(): Food {
    val calories = totalNutrients["ENERC_KCAL"]?.quantity?.toString() ?: "not found"
    val carbs = totalNutrients["CHOCDF"]?.quantity?.toString() ?: "not found"
    val fats = totalNutrients["FAT"]?.quantity?.toString() ?: "not found"
    val protein = totalNutrients["PROCNT"]?.quantity?.toString() ?: "not found"
    val totalWeight = totalWeight.toString()

    // Create and return the Food object
    return Food(
        desc = ingredients.joinToString { it.text },
        calories = calories,
        grams = totalWeight,
        carbs = carbs,
        fats = fats,
        protein = protein,
    )
}
