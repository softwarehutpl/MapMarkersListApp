package app.injector.com.mapmarkerslistapp.di

import app.injector.com.mapmarkerslistapp.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity
}