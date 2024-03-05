package com.example.apppermission.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Main Thread Start ${Thread.currentThread().name}")


        val msgOne = async(start = CoroutineStart.LAZY) {
            //..more code..
            getMessageOne()
        }
        val msgTwo = async(start = CoroutineStart.LAZY) {
            getMessageTwo()
        }

        ///TODO:Synopsis
        /*
        * msgOne and msgTwo are Deferred objects that are not printing values until we used those objects. But still msgOne and msgTwo are executed in parallel
        * Which using system resources.
        * In this circumstances, we put async(start = CoroutineStart.LAZY) to avoid the execution of msgOne and msgTwo until we used them
        * */

        //println("${msgOne.await()} ${msgTwo.await()}")


        println("Main Thread End ${Thread.currentThread().name}")
    }
}

suspend fun getValueOne(): String {
    delay(1000)
    println("ONE")
    return "Hello"
}

suspend fun getValueTwo(): String {
    delay(1000)
    println("TWO")
    return "World"
}