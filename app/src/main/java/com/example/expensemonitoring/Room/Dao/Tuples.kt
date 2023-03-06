package com.example.expensemonitoring.Room.Dao

import androidx.room.ColumnInfo

data class CategoriesTuple(
    @ColumnInfo(name = "month") var categoryMonth: String,
    @ColumnInfo(name = "year") var categoryYear: String,
    @ColumnInfo(name = "amount_sum") var categorySum: Double,
)