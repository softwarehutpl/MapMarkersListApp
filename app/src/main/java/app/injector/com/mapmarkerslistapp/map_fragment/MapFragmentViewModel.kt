package app.injector.com.mapmarkerslistapp.map_fragment

import android.annotation.SuppressLint
import android.location.Location
import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.karumi.dexter.MultiplePermissionsReport
import javax.inject.Inject

interface MapFragmentView : BaseView {
    fun markMyPosition(lat : Double, lng : Double)
    fun setMyLocationEnabled()
}

class MapFragmentViewModel @Inject constructor(private val fusedLocationClient: FusedLocationProviderClient) : BaseViewModel<MapFragmentView>() {

    private val SHHQlatitude = 0.0
    private val SHHQlongitude = 0.0
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0


    @SuppressLint("MissingPermission")
    fun handleCheckedPermissions(report: MultiplePermissionsReport?, isGoogleMapInitialized : Boolean) {
        if (report?.areAllPermissionsGranted()!! && isGoogleMapInitialized) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                updateMyLocation(location)
                view?.markMyPosition(latitude, longitude)
                view?.setMyLocationEnabled()
            }
        }
    }

    private fun updateMyLocation(location: Location?) {
        latitude = location?.latitude ?: SHHQlatitude
        longitude = location?.longitude ?: SHHQlongitude
    }

}