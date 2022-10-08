package com.example.expensemonitoring.Room.TypeConverters

import androidx.room.TypeConverter
import java.util.*

class ExpensesTypeConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }
}