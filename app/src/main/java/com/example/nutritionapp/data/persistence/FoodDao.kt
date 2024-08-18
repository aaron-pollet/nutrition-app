package com.example.nutritionapp.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbFood)

    @Query("SELECT * FROM foods ORDER BY `desc` ASC")
    fun getAllItems(): Flow<List<DbFood>>

    @Query("SELECT * from foods WHERE id = :id")
    fun getItem(id: Int): Flow<DbFood>
}
