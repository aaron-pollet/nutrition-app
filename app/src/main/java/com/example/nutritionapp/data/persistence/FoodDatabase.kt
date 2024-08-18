package com.example.nutritionapp.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [DbFood::class], version = 2)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}

val MIGRATION_1_2 =
    object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Example: Adding a new column to an existing table
            database.execSQL("ALTER TABLE food ADD COLUMN new_column mealType")
        }
    }
