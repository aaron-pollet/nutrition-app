package com.example.nutritionapp.data

import kotlin.random.Random

data class FoodSampler(val name: String, val description: String = "") {
    companion object FoodSampler {
        val sampleFoods =
            mutableListOf(
                "Pizza",
                "Sushi",
                "Spaghetti Bolognese",
                "Tacos",
                "Caesar Salad",
                "Butter Chicken",
                "Chocolate Cake",
            )
        val getOne: () -> com.example.nutritionapp.data.FoodSampler = {
            FoodSampler(
                sampleFoods[Random.nextInt(0, sampleFoods.size)],
                if (Random.nextInt(0, 2) == 0) {
                    "lorem ipsum dolor sit"
                } else {
                    "consectetur adipiscing elit"
                },
            )
        }
        val getAll: () -> List<com.example.nutritionapp.data.FoodSampler> = {
            val list = mutableListOf<com.example.nutritionapp.data.FoodSampler>()
            repeat(10) {
                for (item in sampleFoods) {
                    list.add(
                        FoodSampler(
                            item,
                            if (Random.nextInt(0, 2) == 0) {
                                "lorem ipsum dolor sit"
                            } else {
                                "consectetur adipiscing elit"
                            },
                        ),
                    )
                }
            }
            list
        }
    }
}
