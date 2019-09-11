package app.injector.com.mapmarkerslistapp.list_fragment

import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import javax.inject.Inject

interface ListFragmentView : BaseView {}

class ListFragmentViewModel @Inject constructor() : BaseViewModel<ListFragmentView>() {

}