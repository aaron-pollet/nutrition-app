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

class FoodSamplerAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

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
//        composeTestRule
//            .onNodeWithText(getResourceString(FoodOverviewScreen.Profile.title))
//            .assertIsDisplayed()
        assertEquals(NutritionAppOverviewScreen.Profile.name, navController.currentBackStackEntry?.destination?.route)
    }

    private fun getResourceString(
        @StringRes key: Int,
    ): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }
}
