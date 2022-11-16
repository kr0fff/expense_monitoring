package com.example.expensemonitoring.Room.TypeConverters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.*

class ExpensesTypeConverter {
    @TypeConverter
    fun fromDate(date: LocalDate?): String? {
        return date?.toString()
    }
    @TypeConverter
    fun toDate(dateFormat: String): LocalDate? {
        return LocalDate.parse(dateFormat)
    }
}