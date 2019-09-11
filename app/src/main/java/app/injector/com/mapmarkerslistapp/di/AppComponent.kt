package app.injector.com.mapmarkerslistapp.di

import androidx.lifecycle.ViewModelProvider
import app.injector.com.mapmarkerslistapp.base.BaseApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    ActivityBuildersModule::class,
    AndroidSupportInjectionModule::class,
    FragmentBuildersModule::class,
    ViewModelModule::class
))
interface AppComponent {
    fun inject(app: BaseApp)
    fun getViewModelProviderFactory(): ViewModelProvider.Factory
}