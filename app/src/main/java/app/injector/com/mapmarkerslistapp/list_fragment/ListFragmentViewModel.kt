package app.injector.com.mapmarkerslistapp.list_fragment

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import app.injector.com.mapmarkerslistapp.BR
import app.injector.com.mapmarkerslistapp.repositories.PointRepository
import app.injector.com.mapmarkerslistapp.room.model.Point
import app.injector.com.mapmarkerslistapp.utils.BaseSchedulers
import app.injector.com.mapmarkerslistapp.utils.RxScheduling
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import javax.inject.Inject

interface ListFragmentView : BaseView {}

class ListFragmentViewModel @Inject constructor(private val pointRepository: PointRepository,
                                                private val baseSchedulers: BaseSchedulers) : BaseViewModel<ListFragmentView>() {

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
}