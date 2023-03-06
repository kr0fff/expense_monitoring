package com.example.expensemonitoring.Room.RepositoryTypes

import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.Room.Dao.ExpensesDao
import com.example.expensemonitoring.Room.Entities.Expenses

class RoomCategoriesRepository(
    private val expensesDao: ExpensesDao
) {
    suspend fun getCategoriesRecords(): List<CategoriesTuple?>{
        return expensesDao.getCategories()
    }
    suspend fun addExpenseRecord(expense: Expenses) {
        expensesDao.createExpense(expense)
    }
}