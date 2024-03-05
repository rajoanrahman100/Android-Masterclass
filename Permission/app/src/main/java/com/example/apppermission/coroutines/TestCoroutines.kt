@file:OptIn(DelicateCoroutinesApi::class)

package com.example.apppermission

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {


    runBlocking {

        println("Main Thread Start ${Thread.currentThread().name}")

        /*GlobalScope.launch { //creates a background coroutine that runs on a background thread
            println("Fake Thread One ${Thread.currentThread().name}")
            delay(3000)
            println("Fake Thread Two ${Thread.currentThread().name}")
        }*/

        val job:Job=launch { //Job object allow us to cancel the coroutine or wait for it to finish executing
            println("Fake Thread One ${Thread.currentThread().name}")
            delay(3000)
            println("Fake Thread Two ${Thread.currentThread().name}")
        }

//        runDelay(4000)
        job.join()
        //Cancel the coroutine:: job.cancel()
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

/*
* What is Coroutine Builder?
* Coroutines builders are used to create new coroutines.
* e.g. launch, async, runBlocking
* */

/*
* GlobalScope.launch()
* is a Global Coroutine Builder. It is used to launch new coroutines on the global scope and can survive the entire lifetime of the application
* e.g. File Downloading, Internet Connection,Playing Music
* N.B. Discouraged to use Global.launch(). Use only when required otherwise GlobalScope.launch() will run in the background and consumes memory
* */

/*
* launch()
* is a Local Coroutine Builder. It is used to launch new coroutines on the local scope and can not survive the entire lifetime of the application
* e.g. Some Data Manipulation, Calling Api
* N.B. By default launch()
* Good to know: launch coroutine builder inherits from runBlocking{} builder. Since runBlocking{} builder runs in Main thread, so the child launch coroutines builder runs in the Main thread as well
* */