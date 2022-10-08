package com.example.expensemonitoring.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expensemonitoring.Room.Entities.Categories
import com.example.expensemonitoring.Room.Entities.Expenses
import com.example.expensemonitoring.Room.TypeConverters.ExpensesTypeConverter

@Database(entities = [Categories::class, Expenses::class], version = 0, exportSchema = false)
@TypeConverters(ExpensesTypeConverter::class)
abstract class ExpenseMonitoringDatabase: RoomDatabase() {

}