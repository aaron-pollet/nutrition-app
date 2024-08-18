package com.example.nutritionapp.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbFood::class], version = 2)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}
