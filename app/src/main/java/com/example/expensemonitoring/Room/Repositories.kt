package com.example.expensemonitoring.Room

import android.content.Context
import androidx.room.Room
import com.example.expensemonitoring.Room.Database.AppDatabase
import com.example.expensemonitoring.Room.RepositoryTypes.RoomCategoriesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Repositories {
    private lateinit var applicationContext: Context

    private val database: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "mainDatabase.db")
            .build()
    }
    private val dispatcher : CoroutineDispatcher = Dispatchers.IO

    val categoriesRepository = RoomCategoriesRepository(database.getExpensesDao())

    fun init(context: Context) {
        applicationContext = context
    }
}