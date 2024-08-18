package com.example.nutritionapp.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nutritionapp.FoodApp
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FoodAppNavigationTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun initializeApp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator(),
            )
            FoodApp(navController)
        }
    }

    @Test
    fun homeScreen_Profile_isEnabledAndDisplayed() {
        composeTestRule.onNodeWithContentDescription("navigate to profile screen")
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun startScreen_showsHomeScreen() {
        composeTestRule
            .onNodeWithText(getResourceString(NutritionAppOverviewScreen.Start.title))
            .assertIsDisplayed()
    }

    @Test
    fun clickOn_Profile_NavigateToProfile() {
        composeTestRule
            .onNodeWithContentDescription("navigate to profile screen")
            .performClick()
        assertEquals(NutritionAppOverviewScreen.Profile.name, navController.currentBackStackEntry?.destination?.route)
    }

    private fun getResourceString(
        @StringRes key: Int,
    ): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }

    @Test
    fun foodOverviewScreen_displaysAmountConsumed() {
        composeTestRule
            .onNodeWithContentDescription("navigate to home screen")
            .performClick()

        composeTestRule
            .onNodeWithText("Amount consumed")
            .assertIsDisplayed()
    }

    @Test
    fun foodOverviewScreen_displaysRemainingCalories() {
        composeTestRule
            .onNodeWithContentDescription("navigate to home screen")
            .performClick()

        composeTestRule
            .onNodeWithText("Remaining calories: 0 kcal")
            .assertIsDisplayed()
    }

    @Test
    fun foodOverviewScreen_displaysProtein() {
        composeTestRule
            .onNodeWithContentDescription("navigate to home screen")
            .performClick()

        // Verify "Protein" text is displayed
        composeTestRule
            .onNodeWithText("Protein") // Replace with actual text present in the Food Overview screen
            .assertIsDisplayed()
    }

    @Test
    fun foodOverviewScreen_displaysFat() {
        // Navigate to Food Overview screen
        composeTestRule
            .onNodeWithContentDescription("navigate to home screen")
            .performClick()

        // Verify "Fat" text is displayed
        composeTestRule
            .onNodeWithText("Fat") // Replace with actual text present in the Food Overview screen
            .assertIsDisplayed()
    }

    @Test
    fun foodOverviewScreen_displaysCarbs() {
        // Navigate to Food Overview screen
        composeTestRule
            .onNodeWithContentDescription("navigate to home screen")
            .performClick()

        // Verify "Carbs" text is displayed
        composeTestRule
            .onNodeWithText("Carbs") // Replace with actual text present in the Food Overview screen
            .assertIsDisplayed()
    }
}
