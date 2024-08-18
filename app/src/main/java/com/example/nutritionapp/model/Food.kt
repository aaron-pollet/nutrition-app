package com.example.nutritionapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val desc: String,
    val calories: Double,
    val grams: Double,
    val carbs: Double,
    val fats: Double,
    val protein: Double,
    val mealType: MealType,
)

enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER,
    SNACK,
}
