package com.example.apppermission

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() {

    runBlocking {
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
    }

}

/*
* To cancel a coroutine it should be coroutine cooperative
* Two ways to Make Coroutines Cooperative
* 1. Only those suspending functions that belongs to kotlin.coroutines package will make coroutines cooperative
* 2. delay(), yield(), withContext(), withTimeout() etc are the function that belongs to kotlinx.coroutines package
* */