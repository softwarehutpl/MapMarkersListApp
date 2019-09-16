package app.injector.com.mapmarkerslistapp.map_fragment

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseFragment
import app.injector.com.mapmarkerslistapp.databinding.FragmentMapBinding
import app.injector.com.mapmarkerslistapp.room.model.Point
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.android.support.AndroidSupportInjection

class MapFragment : BaseFragment<MapFragmentView, MapFragmentViewModel, FragmentMapBinding>(), MapFragmentView,
    OnMapReadyCallback, GoogleMap.OnCameraMoveListener {

    companion object {
        fun newInstance() : MapFragment {
            return MapFragment()
        }
    }
    private val MIN_ZOOM_PREFFERENCE = 11f

    private lateinit var myPosition : LatLng
    private lateinit var googleMap: GoogleMap
    private lateinit var visibleMapBounds : LatLngBounds

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setup(R.layout.fragment_map, this, MapFragmentViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        setupMap()
    }

    private fun setupMap() {
        if (!::googleMap.isInitialized) {
            val map = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment?
            map!!.getMapAsync(this)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        if (!::googleMap.isInitialized) {
            googleMap = map!!
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            googleMap.setMinZoomPreference(MIN_ZOOM_PREFFERENCE)
            googleMap.setOnCameraMoveListener(this)
            //todo load points
        }
        checkMyLocationEnablePermissions()
        clearAllMarkers()
        viewModel.provideCachedPoints()
    }

    private fun clearAllMarkers() {
        googleMap.clear()
    }

    override fun showCachedPointsOnTheMap(points: List<Point>) {
        for (point in points) {
            googleMap.addMarker(createMarker(point))
        }
    }

    private fun createMarker(point : Point) : MarkerOptions {
        val bitMapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        val markerOptions = MarkerOptions().position(LatLng(point.latitude, point.longitude)).icon(bitMapDescriptor)
        return markerOptions
    }

    private fun checkMyLocationEnablePermissions() {
        Dexter.withActivity(activity)
            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) { }

                @SuppressLint("MissingPermission")
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    viewModel.handleCheckedPermissions(report, ::googleMap.isInitialized)
                }
            }).check()
    }

    override fun markMyPosition(lat: Double, lng: Double) {
        if (::googleMap.isInitialized) {
            myPosition = LatLng(lat, lng)
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition))
        }
    }

    @SuppressLint("MissingPermission")
    override fun setMyLocationEnabled() {
        googleMap.isMyLocationEnabled = true
    }

    override fun onCameraMove() {
        visibleMapBounds = googleMap.projection.visibleRegion.latLngBounds
    }
}