package com.example.expensemonitoring.Room.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.expensemonitoring.Room.Entities.Categories

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM categories")
    suspend fun getCategoriesRecords(): List<Categories>

    @Delete(entity = Categories::class)
    suspend fun deleteCategory(category: Categories)
}