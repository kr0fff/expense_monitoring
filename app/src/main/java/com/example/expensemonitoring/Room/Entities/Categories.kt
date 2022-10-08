package com.example.expensemonitoring.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Month
import java.time.Year

@Entity(
    tableName = "categories"
)
data class Categories(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "month") var month: Int,
    @ColumnInfo(name = "year") var year: Int
)