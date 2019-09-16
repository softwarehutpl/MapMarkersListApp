package app.injector.com.mapmarkerslistapp.map_fragment

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import app.injector.com.mapmarkerslistapp.repositories.PointRepository
import app.injector.com.mapmarkerslistapp.room.model.Point
import app.injector.com.mapmarkerslistapp.utils.BaseSchedulers
import app.injector.com.mapmarkerslistapp.utils.RxScheduling
import com.google.android.gms.location.FusedLocationProviderClient
import com.karumi.dexter.MultiplePermissionsReport
import javax.inject.Inject

interface MapFragmentView : BaseView {
    fun markMyPosition(lat : Double, lng : Double)
    fun setMyLocationEnabled()
    fun showCachedPointsOnTheMap(points: List<Point>)
}

class MapFragmentViewModel @Inject constructor(private val fusedLocationClient: FusedLocationProviderClient,
                                               private val pointRepository: PointRepository,
                                               private val baseSchedulers: BaseSchedulers) : BaseViewModel<MapFragmentView>() {

    private val SHHQlatitude = 53.14
    private val SHHQlongitude = 23.17
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

    fun provideCachedPoints() {
        pointRepository.getAll()
            .compose(RxScheduling.ioSingle(baseSchedulers))
            .subscribe({points -> onPointsFetched(points)},
                { error -> Log.e("ERROR", error?.message, error)}).disposeOnDetach()
    }

    private fun onPointsFetched(points : List<Point>) {
        if (points.isEmpty()) {
            val fakedPoints = createFakePoints()
            pointRepository.insert(fakedPoints)
                .compose(RxScheduling.ioCompletable(baseSchedulers))
                .subscribe({}, { error -> Log.e("ERROR", error?.message, error)}).disposeOnDetach()
            view?.showCachedPointsOnTheMap(fakedPoints)

        } else {
            view?.showCachedPointsOnTheMap(points)
        }
    }


    private fun updateMyLocation(location: Location?) {
        latitude = location?.latitude ?: SHHQlatitude
        longitude = location?.longitude ?: SHHQlongitude
    }

    private fun createFakePoints() : List<Point> {
        val points = ArrayList<Point>()
        // POINTS INSIDE THE BIALYSTOK
        points.add(createFakePoint("SoftwareHut", 53.140, 23.174, 5.0F))
        points.add(createFakePoint("Atrium Biała", 53.122, 23.179, 3.0F))
        points.add(createFakePoint("Pałac Branickich", 53.131, 23.168, 4.0F))
        points.add(createFakePoint("Murki PB", 53.118, 23.150, 6.0F))
        // POINTS OUTSIDE THE BIALYSTOK
        points.add(createFakePoint("Supraśl", 53.210, 23.339, 4.0F))
        points.add(createFakePoint("Choroszcz", 53.141, 22.970, 1.0F))
        points.add(createFakePoint("Juchnowiec Kościelny", 53.018, 23.141, 3.0F))
        points.add(createFakePoint("Klekocin", 53.096, 23.116, 2.0F))
        return points
    }

    private fun createFakePoint(name : String, latitude : Double, longitude : Double, rating : Float) : Point {
        val point = Point()
        point.name = name
        point.latitude = latitude
        point.longitude = longitude
        point.rating = rating
        return point
    }
}