package app.injector.com.mapmarkerslistapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.injector.com.mapmarkerslistapp.list_fragment.ListFragmentViewModel
import app.injector.com.mapmarkerslistapp.main.MainViewModel
import app.injector.com.mapmarkerslistapp.map_fragment.MapFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapFragmentViewModel::class)
    abstract fun bindMapFragmentViewModel(viewModel: MapFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    abstract fun bindListFragmentViewModel(viewModel: ListFragmentViewModel) : ViewModel
}