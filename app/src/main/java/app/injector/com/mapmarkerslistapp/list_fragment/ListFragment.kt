package app.injector.com.mapmarkerslistapp.list_fragment

import android.os.Bundle
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseFragment
import app.injector.com.mapmarkerslistapp.databinding.FragmentListBinding
import com.google.android.gms.maps.model.LatLngBounds
import dagger.android.support.AndroidSupportInjection

class ListFragment : BaseFragment<ListFragmentView, ListFragmentViewModel, FragmentListBinding>(), ListFragmentView {

    companion object {
        fun newInstance() : ListFragment {
            return ListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setup(R.layout.fragment_list, this, ListFragmentViewModel::class.java)
    }

    fun updatePointsList(bounds : LatLngBounds) {
        viewModel.updatePointsList(bounds)
    }
}