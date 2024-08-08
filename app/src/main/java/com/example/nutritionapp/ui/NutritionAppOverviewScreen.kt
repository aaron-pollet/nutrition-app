package com.example.nutritionapp.ui

import androidx.annotation.StringRes
import com.example.nutritionapp.R

enum class NutritionAppOverviewScreen(
    @StringRes val title: Int,
) {
    Start(R.string.app_home_title),
    Exercise(R.string.app_exercise_title),
    Add(R.string.app_add_new_item_title),
    Weight(R.string.app_weight_tracking_title),
    Profile(R.string.app_profile_title),
}
