package app.injector.com.mapmarkerslistapp.list_fragment

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import app.injector.com.mapmarkerslistapp.BR
import app.injector.com.mapmarkerslistapp.model.Point
import javax.inject.Inject

interface ListFragmentView : BaseView {}

class ListFragmentViewModel @Inject constructor() : BaseViewModel<ListFragmentView>() {

    val items: ObservableList<Point> = ObservableArrayList()

    val itemBinding = ItemBinding.of<Point>(BR.item , R.layout.list_fragment_item)

}