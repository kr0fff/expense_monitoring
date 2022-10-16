package com.example.expensemonitoring

import kotlinx.coroutines.*
import java.time.Month

fun main(){
   var res = runBlocking {
        val asyncPart = async {
            return@async suspendComputation(6)
        }
        asyncPart.await()
    }

    print(res)
}


suspend fun suspendComputation(num: Int): Int {
    var encounter = num
    var res = 0
    while (encounter > 0){
        res += encounter
        encounter--
    }
    return res
}
fun String.intOrString() = try {
    toDouble()
} catch(e: NumberFormatException) {
    null
}