package com.example.nutritionapp.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutritionapp.model.Food
import com.example.nutritionapp.model.MealType

@Entity(tableName = "foods")
class DbFood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val desc: String,
    val calories: String,
    val grams: String,
    val carbs: String,
    val fats: String,
    val protein: String,
    val mealType: MealType,
)

fun Food.asDbFood() =
    DbFood(
        desc = desc,
        calories = calories.toString(),
        grams = grams.toString(),
        carbs = carbs.toString(),
        fats = fats.toString(),
        protein = protein.toString(),
        mealType = mealType,
    )

fun DbFood.asDomainFood() =
    Food(
        desc = desc,
        calories = calories.toDouble(),
        grams = grams.toDouble(),
        carbs = carbs.toDouble(),
        fats = fats.toDouble(),
        protein = protein.toDouble(),
        mealType = mealType,
    )

fun List<DbFood>.asDomainFoods() =
    map {
        it.asDomainFood()
    }
