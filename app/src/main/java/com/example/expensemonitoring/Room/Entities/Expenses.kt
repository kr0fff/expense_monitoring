package com.example.expensemonitoring.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Categories::class,
            parentColumns = ["id"],
            childColumns = ["categories_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Expenses(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "date") var date: Date,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "categories_id") var categoriesId: Long
)
