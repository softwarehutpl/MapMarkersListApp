package app.injector.com.mapmarkerslistapp.main

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.base.BaseActivity
import app.injector.com.mapmarkerslistapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainView, MainViewModel, ActivityMainBinding>(), MainView {

    lateinit var mainActivitySectionsAdapter: MainActivitySectionsAdapter
    private val defaultSelectedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_main, this, MainViewModel::class.java)
        initTabLayout()
    }

    private fun initTabLayout() {
        mainActivitySectionsAdapter = MainActivitySectionsAdapter(this, supportFragmentManager)
        mainActivitySectionsAdapter.initializeFragments()
        view_pager.adapter = mainActivitySectionsAdapter
        view_pager.currentItem = defaultSelectedItem
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.main_map_tab_title)))
        tab_layout.addTab(tab_layout.newTab().setText(getString(R.string.main_map_list_tab_title)))
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) { }
            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    mainActivitySectionsAdapter.checkTabAndUpdateList(tab.position)
                }
            }
        })
    }
}
