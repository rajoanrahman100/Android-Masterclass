package com.example.apppermission

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {

    println("Main Thread Start ${Thread.currentThread().name}")
    //

    runBlocking {
        val job: Deferred<String> = async {
            println("Fake Thread One ${Thread.currentThread().name}")
            delay(3000)
            println("Fake Thread Two ${Thread.currentThread().name}")
            "We can return anything from async builder function"
        }
        //job.join() or job.cancel()
        val name =job.await()
        println("Main Thread End ${Thread.currentThread().name} and Return async value is '$name' ")
    }
}