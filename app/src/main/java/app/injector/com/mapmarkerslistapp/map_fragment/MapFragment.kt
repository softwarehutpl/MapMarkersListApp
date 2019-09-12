package app.injector.com.mapmarkerslistapp.map_fragment

import android.os.Bundle
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseFragment
import app.injector.com.mapmarkerslistapp.databinding.FragmentMapBinding
import dagger.android.support.AndroidSupportInjection

class MapFragment : BaseFragment<MapFragmentView, MapFragmentViewModel, FragmentMapBinding>(), MapFragmentView {

    companion object {
        fun newInstance() : MapFragment {
            return MapFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setup(R.layout.fragment_map, this, MapFragmentViewModel::class.java)
    }

}