package com.example.expensemonitoring

import android.widget.NumberPicker
import androidx.lifecycle.viewModelScope
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

class Person(var name: String, var salary: Int)

fun main() {
//    runBlocking { mutexTest() }

    /*var list = List(10){
        index -> Person("$index", index* 10)
    }
    var sortedList = list.sortedWith(compareBy({it.salary}, {it.name})).reversed()
    println(list == sortedList)*/
    runBlocking {

       /* var res = async {
            for (i in 1 until 10) {
                println("iterator: $i")
            }
        }.await()*/

        coroutineScope {
            withContext(Dispatchers.IO){
                for (i in 1..10){
                    println("Deferred coroutine called with iterator: $i")
                }
            }
            launch {
                for (i in 1..10){
                    println("Other called with iterator: $i")
                }
            }
        }

       /* coroutineScope {
            delay(1000L)
            println("1")
        }

        println("main")
        launch {
            println("2")
        }*/

    }
}

suspend fun refreshCategories() {
    coroutineScope {
        withContext(Dispatchers.IO) {
            println(coroutineContext)
        }
    }
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