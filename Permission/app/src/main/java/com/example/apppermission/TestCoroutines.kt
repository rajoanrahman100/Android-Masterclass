@file:OptIn(DelicateCoroutinesApi::class)

package com.example.apppermission

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {


    runBlocking {

        println("Main Thread Start ${Thread.currentThread().name}")

        GlobalScope.launch { //creates a background coroutine that runs on a background thread
            println("Fake Thread One ${Thread.currentThread().name}")
            delay(3000)
            println("Fake Thread Two ${Thread.currentThread().name}")
        }

//    Thread.sleep(4000) //Block the main thread and waits for 2.5 seconds until the coroutine is finished

        runDelay(4000)

        println("Main Thread End ${Thread.currentThread().name}")
    }


}

suspend fun runDelay(milliSeconds: Long) {
    delay(milliSeconds)
}

/*
* What is Suspending Function?
* A function with a "suspend" keyword is known as a suspending function. A suspending function is a function that can be paused and resumed at a later time.
* Suspended function are only allowed to be called from coroutine or from another suspending function
* */