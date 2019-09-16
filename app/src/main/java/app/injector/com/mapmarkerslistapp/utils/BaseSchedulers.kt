package app.injector.com.mapmarkerslistapp.utils

import io.reactivex.Scheduler

interface BaseSchedulers {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
    fun single(): Scheduler
}