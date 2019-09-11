package app.injector.com.mapmarkerslistapp.map_fragment

import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import javax.inject.Inject

interface MapFragmentView : BaseView {}

class MapFragmentViewModel @Inject constructor() : BaseViewModel<MapFragmentView>() {

}