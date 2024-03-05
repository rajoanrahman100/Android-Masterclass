package com.example.apppermission

import kotlinx.coroutines.*

fun main() {

    runBlocking {
        println("Main Thread Start ${Thread.currentThread().name}")


        val msgOne=async {
            //..more code..
            getMessageOne()
        }
        val msgTwo=async {
            getMessageTwo()
        }

        println("${msgOne.await()} ${msgTwo.await()}")

        println("Main Thread End ${Thread.currentThread().name}")
    }
}

suspend fun getMessageOne():String{
    delay(1000)
    println("ONE")
    return "Hello"
}

suspend fun getMessageTwo():String{
    delay(1000)
    println("TWO")
    return "World"
}

/*
* One thing we should keep in mind:
* Inside co routines, by default all functions are executed in sequential
* */