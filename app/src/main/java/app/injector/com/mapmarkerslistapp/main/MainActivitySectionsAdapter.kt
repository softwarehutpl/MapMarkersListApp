package app.injector.com.mapmarkerslistapp.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.injector.com.mapmarkerslistapp.R
import app.injector.com.mapmarkerslistapp.list_fragment.ListFragment
import app.injector.com.mapmarkerslistapp.map_fragment.MapFragment

class MainActivitySectionsAdapter(private val context : Context, private val fm : FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        val MAP_TAB_INDEX = 0
        val MARKERS_LIST_TAB_INDEX = 1
    }

    private var tabFragmentsIsInitialized = false
    private lateinit var listFragment : ListFragment
    private lateinit var mapFragment : MapFragment

    private val TABS = intArrayOf(MAP_TAB_INDEX, MARKERS_LIST_TAB_INDEX)

    override fun getItem(position: Int): Fragment {
        if (TABS[position] == MAP_TAB_INDEX) {
            return mapFragment
        }
        return listFragment
    }

    fun initializeFragments() {
        if (!tabFragmentsIsInitialized) {
            mapFragment = MapFragment.newInstance()
            listFragment = ListFragment.newInstance()
            tabFragmentsIsInitialized = true
        }
    }

    override fun getCount(): Int {
        return TABS.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (TABS[position] == MAP_TAB_INDEX) {
            return context.resources.getString(R.string.main_map_tab_title)
        }
        return context.resources.getString(R.string.main_map_list_tab_title)
    }
}