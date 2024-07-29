package data

import kotlin.random.Random

data class Food(val name: String, val description: String = "") {
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
        val getOne: () -> Food = {
            Food(
                sampleFoods[Random.nextInt(0, sampleFoods.size)],
                if (Random.nextInt(0, 2) == 0) {
                    "lorem ipsum dolor sit"
                } else {
                    "consectetur adipiscing elit"
                },
            )
        }
        val getAll: () -> List<Food> = {
            val list = mutableListOf<Food>()
            repeat(10) {
                for (item in sampleFoods) {
                    list.add(
                        Food(
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
