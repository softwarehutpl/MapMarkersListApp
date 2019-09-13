package app.injector.com.mapmarkerslistapp.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides

@Module
open class ManagerProviderModule {

    @Provides
    fun provideFusedLocationClient(context : Context) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}