package com.example.nutritionapp

import android.app.Application
import com.example.nutritionapp.data.AppContainer
import com.example.nutritionapp.data.DefaultAppContainer

class NutritionApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
