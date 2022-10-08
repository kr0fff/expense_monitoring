package com.example.expensemonitoring.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensemonitoring.Room.Entities.Categories
import com.example.expensemonitoring.Room.Entities.Expenses

@Database(entities = [Categories::class, Expenses::class], version = 0, exportSchema = false)
abstract class ExpenseMonitoringDatabase: RoomDatabase() {

}