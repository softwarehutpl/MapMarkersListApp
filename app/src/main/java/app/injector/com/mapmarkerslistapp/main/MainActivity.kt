package app.injector.com.mapmarkerslistapp.main

import android.os.Bundle
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseActivity
import app.injector.com.mapmarkerslistapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainView, MainViewModel, ActivityMainBinding>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_main, this, MainViewModel::class.java)
    }
}
