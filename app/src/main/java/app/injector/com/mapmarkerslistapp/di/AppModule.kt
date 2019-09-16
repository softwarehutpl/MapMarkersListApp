package app.injector.com.mapmarkerslistapp.di

import android.content.Context
import app.injector.com.mapmarkerslistapp.base.BaseApp
import app.injector.com.mapmarkerslistapp.utils.BaseSchedulers
import app.injector.com.mapmarkerslistapp.utils.BaseSchedulersImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val app : BaseApp) {

    @Provides
    fun provideApp() = app

    @Singleton
    @Provides
    fun provideKourtsSchedulers(): BaseSchedulers = BaseSchedulersImpl()

    @Provides
    fun provideContext(): Context = app
}