package com.example.nutritionapp.ui

import com.example.nutritionapp.ui.viewmodel.food.FoodViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

private const val SOME_FOOD_NAME = "Apple"

class FoodSamplerOverviewModelTest {
    private val viewModel = FoodViewModel()

    @Test
    fun viewModel_StarsWith_EmptyNewFoodName() {
        assertEquals("", viewModel.foodUiState.value.newFoodName)
    }

    @Test
    fun `starts with empty description`() {
        assertEquals("", viewModel.foodUiState.value.newFoodDescription)
    }

    @Test
    fun `can set food name`() {
        viewModel.setNewFoodName(SOME_FOOD_NAME)
        assertEquals(SOME_FOOD_NAME, viewModel.foodUiState.value.newFoodName)
    }

    @Test
    fun `save food clears name`() {
        viewModel.setNewFoodName(SOME_FOOD_NAME)
        viewModel.addFood()
        assertEquals("", viewModel.foodUiState.value.newFoodName)
    }
}
