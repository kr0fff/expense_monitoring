package com.example.expensemonitoring.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "expenses"
)
data class Expenses(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "date") var date: String, // must be a Date in format YYYY-MM-DD
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "amount") var amount: Double,
){
    companion object {
        fun toExpense(date: String, description: String, amount: Double) = Expenses(
            id = 0,
            date = date,
            description = description,
            amount = amount
        )
    }
}
