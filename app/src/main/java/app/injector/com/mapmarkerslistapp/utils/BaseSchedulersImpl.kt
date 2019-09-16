package app.injector.com.mapmarkerslistapp.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BaseSchedulersImpl : BaseSchedulers {

    override fun computation() = Schedulers.computation()

    override fun main() = AndroidSchedulers.mainThread()!!

    override fun io() = Schedulers.io()

    override fun single() = Schedulers.single()
}