package app.injector.com.mapmarkerslistapp.di

import app.injector.com.mapmarkerslistapp.base.BaseApp
import dagger.Module
import dagger.Provides

@Module
open class AppModule(private val app : BaseApp) {

    @Provides
    fun provideApp() = app
}