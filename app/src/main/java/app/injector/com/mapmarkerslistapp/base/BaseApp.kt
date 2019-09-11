package app.injector.com.mapmarkerslistapp.base

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import app.injector.com.mapmarkerslistapp.di.AppComponent
import app.injector.com.mapmarkerslistapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class BaseApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = AppInjector.init(this)
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
