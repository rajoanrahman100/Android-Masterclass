package com.example.apppermission

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() {

    runBlocking {
        println("Main Thread Start ${Thread.currentThread().name}")

        withTimeout(2000) {
            try {
                for (i in 0..500) {
                    println("Fake Thread $i ${Thread.currentThread().name}")
                    delay(300)
                }
            }catch (ex:TimeoutCancellationException){
                println("Exception Caught ${ex.message}")
            }finally {
                println("Close resource finally")

            }
        }
        println("Main Thread End ${Thread.currentThread().name}")
    }
}