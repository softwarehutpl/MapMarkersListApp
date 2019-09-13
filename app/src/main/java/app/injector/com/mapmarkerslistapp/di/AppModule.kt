package app.injector.com.mapmarkerslistapp.di

import android.content.Context
import app.injector.com.mapmarkerslistapp.base.BaseApp
import dagger.Module
import dagger.Provides

@Module
open class AppModule(private val app : BaseApp) {

    @Provides
    fun provideApp() = app

    @Provides
    fun provideContext(): Context = app
}