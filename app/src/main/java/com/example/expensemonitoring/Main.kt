package com.example.expensemonitoring

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

private val mutex = Mutex()  // our hero ;-)
private var counterWithMutex = 0
private var counterNoMutex = 0

fun main( ) {
//    runBlocking { mutexTest() }

    val str = "Holy goodness"
    println(str.trim())

}
inline fun <reified T> genericsExample(value: T) {
    println(value)
    println("Type of T: ${T::class.java}")
}
suspend fun mutexTest() {
    val job1NoMutex = CoroutineScope(Default).launch {
        for (i in 1..500) {
            incrementCounterByTenNoMutex()
        }
    }

    val job2NoMutex = CoroutineScope(Default).launch {
        for (i in 1..500) {
            incrementCounterByTenNoMutex()
        }
    }

    val job3WithMutex = CoroutineScope(Default).launch {
        for (i in 1..500) {
            incrementCounterByTenWithMutex()
        }
    }

    val job4WithMutex = CoroutineScope(Default).launch {
        for (i in 1..500) {
            incrementCounterByTenWithMutex()
        }
    }

    joinAll(job1NoMutex, job2NoMutex, job3WithMutex, job4WithMutex)

    println("  No Mutex Tally: $counterNoMutex")
    println("With Mutex Tally: $counterWithMutex")
}

private suspend fun incrementCounterByTenWithMutex() {
    mutex.withLock {
        for (i in 0 until 10) {
            counterWithMutex++
        }
    }
}

private fun incrementCounterByTenNoMutex() {
    for (i in 0 until 10) {
        counterNoMutex++
    }
}