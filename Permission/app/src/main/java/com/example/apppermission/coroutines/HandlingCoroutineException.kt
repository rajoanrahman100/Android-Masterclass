package com.example.apppermission.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        println("Main Thread Start ${Thread.currentThread().name}")

        val job = launch {
            try {
                for (i in 0..100) {
                    println("Fake Thread $i ${Thread.currentThread().name}")
                    delay(2)
                }
            } catch (ex: CancellationException) {
                println("Exception Caught Safety ${ex.message}")
            } finally {
                //Basically We can't call any suspended functions from finally block
                //But with the help of
                withContext(NonCancellable) {
                    getDelay(1000)
                }
                println("Close resource finally")
            }
        }
        delay(10)
        job.cancel(CancellationException("My Cancellable Message")) //This message will print in Exception Caught by ${ex.message}
        job.join()
        println("Main Thread END ${Thread.currentThread().name}")
    }
}

suspend fun getDelay(milliSeconds: Long) {
    delay(milliSeconds)
}

/*
* Any suspending function that belongs to kotlin.coroutines package will make coroutines cooperative
* and can be cancellable and will trow the CancellationException exception
* */