package app.injector.com.mapmarkerslistapp.base

interface BaseView {
    fun showMessage(message: String?)
    fun showMessage(stringResId: Int, arg: String)
    fun showMessage(stringResId: Int)
}