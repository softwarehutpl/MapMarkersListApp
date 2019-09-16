package app.injector.com.mapmarkerslistapp.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:floatText")
fun setFloat(view : TextView, value : Float) {
    view.setText(value.toString())
}