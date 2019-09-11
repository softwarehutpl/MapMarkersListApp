package app.injector.com.mapmarkerslistapp.main

import app.injector.com.mapmarkerslistapp.base.BaseView
import app.injector.com.mapmarkerslistapp.base.BaseViewModel
import javax.inject.Inject

interface MainView : BaseView {}

class MainViewModel @Inject constructor() : BaseViewModel<MainView>() {

}