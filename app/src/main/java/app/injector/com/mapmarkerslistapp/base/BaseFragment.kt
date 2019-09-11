package app.injector.com.mapmarkerslistapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import app.injector.com.mapmarkerslistapp.BR
import com.byoutline.secretsauce.di.Injectable
import javax.inject.Inject

open class BaseFragment<VIEW : BaseView, VM : BaseViewModel<VIEW>, BINDING : ViewDataBinding> : Fragment(), BaseView,
    Injectable {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    lateinit var binding: BINDING
    lateinit var viewModel: VM
    private var layoutId: Int = 0
    private lateinit var view : VIEW
    private lateinit var clazz: Class<VM>

    fun setup(layoutId : Int, view : VIEW, clazz: Class<VM>) {
        this.layoutId = layoutId
        this.view = view
        this.clazz = clazz
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<BINDING>(inflater, layoutId, container, false).also { binding = it }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelProviderFactory).get(clazz)
        viewModel.onAttach(view)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun showMessage(message: String?) { Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }

    override fun showMessage(stringResId: Int, arg: String) { showMessage(getString(stringResId)) }

    override fun showMessage(stringResId: Int) { showMessage(getString(stringResId)) }
}