package com.example.expensemonitoring

import java.time.Month

fun main(){
   var month = Month.SEPTEMBER
    print(month.value)
}

fun String.intOrString() = try {
    toDouble()
} catch(e: NumberFormatException) {
    null
}