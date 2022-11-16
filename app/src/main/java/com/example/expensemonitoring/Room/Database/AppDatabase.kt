package com.example.expensemonitoring.Room.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensemonitoring.Room.Dao.ExpensesDao
import com.example.expensemonitoring.Room.Entities.Expenses
import com.example.expensemonitoring.Room.TypeConverters.ExpensesTypeConverter

@Database(
    version = 1,
    entities = [
        Expenses::class
    ]
)
@TypeConverters(ExpensesTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getExpensesDao(): ExpensesDao
}