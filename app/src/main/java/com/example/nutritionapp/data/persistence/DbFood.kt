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
        calories = calories,
        grams = grams,
        carbs = carbs,
        fats = fats,
        protein = protein,
        mealType = mealType,
    )

fun DbFood.asDomainFood() =
    Food(
        desc = desc,
        calories = calories,
        grams = grams,
        carbs = carbs,
        fats = fats,
        protein = protein,
        mealType = mealType,
    )

fun List<DbFood>.asDomainFoods() =
    map {
        it.asDomainFood()
    }
