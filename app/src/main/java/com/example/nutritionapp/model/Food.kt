package com.example.nutritionapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val desc: String,
    val calories: String,
    val grams: String,
    val carbs: String,
    val fats: String,
    val protein: String,
)
