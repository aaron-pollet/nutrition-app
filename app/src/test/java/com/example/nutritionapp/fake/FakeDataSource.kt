package com.example.nutritionapp.fake

import com.example.nutritionapp.network.ApiFood
import com.example.nutritionapp.network.Ingredient
import com.example.nutritionapp.network.Nutrient
import com.example.nutritionapp.network.NutrientKCal
import com.example.nutritionapp.network.ParsedIngredient

object FakeDataSource {
    private val calories: Double = 10.0
    private val totalWeight: Double = 20.0
    private val uri = "uri"
    private val totalNutrients =
        mapOf(
            "label" to
                Nutrient(
                    label = "label",
                    quantity = 10.0,
                    unit = "unit",
                ),
        )
    private val totalDaily =
        mapOf(
            "label" to
                Nutrient(
                    label = "label",
                    quantity = 10.0,
                    unit = "unit",
                ),
        )
    private val ingredients =
        listOf(
            Ingredient(
                text = "text",
                parsed =
                    listOf(
                        ParsedIngredient(
                            quantity = 10.0,
                            measure = "measure",
                            food = "food",
                            foodId = "foodId",
                            weight = 10.0,
                            nutrients =
                                mapOf(
                                    "label" to
                                        Nutrient(
                                            label = "label",
                                            quantity = 10.0,
                                            unit = "unit",
                                        ),
                                ),
                        ),
                    ),
            ),
        )
    private val totalNutrientsKCal =
        mapOf(
            "label" to
                NutrientKCal(
                    label = "label",
                    quantity = 10.0,
                    unit = "unit",
                ),
        )

    val fakeApiFood =
        ApiFood(
            uri = uri,
            calories = calories,
            totalWeight = totalWeight,
            totalNutrients = totalNutrients,
            totalDaily = totalDaily,
            ingredients = ingredients,
            totalNutrientsKCal = totalNutrientsKCal,
        )
}
