@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.nutritionapp.ui

import com.example.nutritionapp.fake.FakeApiFoodRepository
import com.example.nutritionapp.ui.viewmodel.food.FoodViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class FoodSamplerOverviewModelTest {
    private val SOME_FOOD_NAME = "Apple"
    lateinit var viewModel: FoodViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun initializeViewModel() {
        viewModel = FoodViewModel(FakeApiFoodRepository())
    }

    @Test
    fun viewModel_StarsWith_unchecked_Checkbox() {
        viewModel.setNewFoodName(SOME_FOOD_NAME)
        assertEquals(viewModel.uiState.value.newFoodName, SOME_FOOD_NAME)
    }
}

class TestDispatchersRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
