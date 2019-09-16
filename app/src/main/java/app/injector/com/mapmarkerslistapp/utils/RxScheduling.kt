package app.injector.com.mapmarkerslistapp.utils

import io.reactivex.CompletableTransformer
import io.reactivex.SingleTransformer

object RxScheduling {
    fun <T> ioSingle(baseSchedulers: BaseSchedulers): SingleTransformer<T, T> =
        SingleTransformer { observable ->
            observable.subscribeOn(baseSchedulers.io())
                .observeOn(baseSchedulers.main())
        }

    fun ioCompletable(baseSchedulers: BaseSchedulers) : CompletableTransformer =
        CompletableTransformer { completable ->
            completable.subscribeOn(baseSchedulers.io())
                .observeOn(baseSchedulers.main())
        }
}