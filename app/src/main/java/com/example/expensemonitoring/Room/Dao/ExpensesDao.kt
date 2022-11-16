package com.example.expensemonitoring.Room.Dao

import androidx.room.*
import com.example.expensemonitoring.Room.Entities.Expenses

@Dao
interface ExpensesDao {
    @Query("SELECT * FROM expenses " +
            "WHERE strftime('%m', date) = :month AND strftime('%Y', date) = :year")
    suspend fun getExpenses(month: String, year: String): List<Expenses?>

    @Query("SELECT strftime('%m', date) AS month, strftime('%Y', date) AS year," +
            " SUM(amount) AS amount_sum FROM expenses GROUP BY month, year")
    suspend fun getCategories(): List<CategoriesTuple?>

    @Delete(entity = Expenses::class)
    suspend fun deleteExpense(expense: Expenses)

    @Update(entity = Expenses::class)
    suspend fun updateExpense(expense:Expenses)

    @Insert(entity = Expenses::class)
    suspend fun createExpense(expense: Expenses)
}