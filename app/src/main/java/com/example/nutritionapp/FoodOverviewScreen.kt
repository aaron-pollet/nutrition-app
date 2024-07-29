package com.example.nutritionapp

import androidx.annotation.StringRes

enum class FoodOverviewScreen(
    @StringRes val title: Int,
) {
    Start(R.string.app_home_title),
    Exercise(R.string.app_exercise_title),
    Add(R.string.app_add_new_item_title),
    Weight(R.string.app_weight_tracking_title),
    Profile(R.string.app_profile_title),
}
