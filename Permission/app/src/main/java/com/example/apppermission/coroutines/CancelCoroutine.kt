package com.example.apppermission

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    ///TODO: Cancel Coroutine using delay() and yield()
    /*runBlocking {
        println("Main Thread Start ${Thread.currentThread().name}")
        val job: Job = launch {
            for (i in 0..100) {
                println("Fake Thread $i ${Thread.currentThread().name}")
                //Thread.sleep(100) //Not cooperative so job.cancel() will not work
                //delay(100) //Cooperative
                yield()
            }
        }
        delay(3)
        job.cancel()
        job.join()
        //or, job.cancelAndJoin()
        println("Main Thread End ${Thread.currentThread().name}")
    }*/

    ///TODO: Cancel Coroutine using activeFlag
    runBlocking {
        println("Main Thread Start ${Thread.currentThread().name}")
        val job: Job = launch(Dispatchers.Default) {
            for (i in 0..100) {
                if (!isActive) { //activeFlag to check if coroutine is still active
                    return@launch
                }
                Thread.sleep(5)
                println("Fake Thread $i ${Thread.currentThread().name}")
            }
        }
        delay(10)
        job.cancelAndJoin()
        //or, job.cancelAndJoin()
        println("Main Thread End ${Thread.currentThread().name}")
    }


}

/*
* To cancel a coroutine it should be coroutine cooperative
* Two ways to Make Coroutines Cooperative
* 1. a. Only those suspending functions that belongs to kotlin.coroutines package will make coroutines cooperative
*    b. delay(), yield(), withContext(), withTimeout() etc are the function that belongs to kotlinx.coroutines package
* 2. Use activeFlag to check if coroutine is still active
* */