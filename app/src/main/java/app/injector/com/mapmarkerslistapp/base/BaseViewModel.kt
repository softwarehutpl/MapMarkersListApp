package app.injector.com.mapmarkerslistapp.base

import android.util.Log
import com.byoutline.secretsauce.lifecycle.AttachableViewModelRx
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

open class BaseViewModel <T:BaseView> : AttachableViewModelRx<T>() {

    protected val defaultErrorConsumer: Consumer<Throwable> = Consumer { defaultErrorAction(it) }

    protected val defaultErrorAction: ((throwable: Throwable)->Unit) = { _: Throwable ->
        //todo something goes wrong
    }

    override fun onCleared() {
        super.onCleared()
    }
}