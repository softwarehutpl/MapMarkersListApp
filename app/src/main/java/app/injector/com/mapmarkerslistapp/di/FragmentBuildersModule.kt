package app.injector.com.mapmarkerslistapp.di

import app.injector.com.mapmarkerslistapp.list_fragment.ListFragment
import app.injector.com.mapmarkerslistapp.map_fragment.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMapFragment() : MapFragment

    @ContributesAndroidInjector
    abstract fun contributeListFragment() : ListFragment
}