package com.example.expensemonitoring.Room.RepositoryTypes

import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.Room.Dao.ExpensesDao

class RoomCategoriesRepository(
    private val expensesDao: ExpensesDao
) {
    suspend fun getCategoriesRecords(): List<CategoriesTuple?>{
        return expensesDao.getCategories()
    }
}