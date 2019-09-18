# MapMarkersListApp

## Description
Simple mobile application for displaying markers on the map written in MVVM architecture pattern using libraries:
- Dagger2
- RXJava
- Room Database
- GMS Play Services

## Version
- minSdkVersion 23
- targetSdkVersion 29

## Clone
- Clone this repo to your local machine using `https://github.com/softwarehutpl/MapMarkersListApp.git`

## Before launch

- To launch this demo we need configure our own Google Map Api Key by add code in Manifest.xml file like below:

```xml
<meta-data android:name="@string/google_map_geo_api_key_name"
           android:value="--your google map API key--"/>
```

- ...and rebuild project

## Features
- This applications allows to display points on the map fragment and on the list fragment but only these one which 
are currently visible on the map as list items. All selecting and choose points which fits to map bounds source code is:

```kotlin
    val items: ObservableList<Point> = ObservableArrayList()
    val itemBinding = ItemBinding.of<Point>(BR.item , R.layout.list_fragment_item)

    fun updatePointsList(bounds : LatLngBounds) {
        pointRepository.getAll()
            .compose(RxScheduling.ioSingle(baseSchedulers))
            .subscribe({points -> selectPointsInsideVisibleBounds(points, bounds)}, {error -> Log.e("ERROR", error?.message, error)})
            .disposeOnDetach()
    }

    private fun selectPointsInsideVisibleBounds(points: List<Point>, bounds: LatLngBounds) {
        items.clear()
        for (point in points) {
            if (bounds.contains(LatLng(point.latitude, point.longitude))) {
                items.add(point)
            }
        }
    }
```

- Object `LatLngBounds` it's a rectangle represents visible part of the map. To update bounds map fragment must implement `GoogleMap.OnCameraMoveListener` and  `OnMapReadyCallback` interface. Before update bounds we need to define our map:

```kotlin
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
```
- After mapping `SupportMapFragment` object from layout xml file method `onMapReady` adjusting its features needed for the further features:

```kotlin
    private lateinit var googleMap: GoogleMap
    
    ...
    
    override fun onMapReady(map: GoogleMap?) {
        if (!::googleMap.isInitialized) {
            googleMap = map!!
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            googleMap.setMinZoomPreference(MIN_ZOOM_PREFFERENCE)
            googleMap.setOnCameraMoveListener(this)
            googleMap.setOnMapClickListener {
                it -> print("Your location is: ${it.latitude} AND ${it.longitude}")
                // DO STUFF WHEN MAP IS CLICKED
            }
        }
        checkMyLocationEnablePermissions()
        clearAllMarkers()
        viewModel.provideCachedPoints()
    }
```

- Our map is defined in `MapFragment` and with every move on the screen invoke code below:

```kotlin
   private lateinit var visibleMapBounds : LatLngBounds // definition of visible bounds

   override fun onCameraMove() {
        visibleMapBounds = googleMap.projection.visibleRegion.latLngBounds
    }
```
- With visible bounds we can update list by pass visibleBounds to ListFragment through `SectionsAdapter` like below:

```kotlin
fun checkTabAndUpdateList(position: Int) {
        if (position == MARKERS_LIST_TAB_INDEX) {
            listFragment.updatePointsList(mapFragment.getCurrentMapBounds())
        }
    }
```

- App contains created faked points stored in local cache, you can create your own by fetching from f.g firebase and then convert to `Point.class` defined in MapMarkersListApp (don't confuse with `com.google.maps.android.data.Point.class`) or changing points data in code below:

```kotlin
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
```    

