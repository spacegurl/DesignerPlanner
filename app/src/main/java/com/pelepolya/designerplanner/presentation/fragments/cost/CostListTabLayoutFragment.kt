package com.pelepolya.designerplanner.presentation.fragments.cost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.presentation.stateholder.adapter.ProjectPagerAdapter

class CostListTabLayoutFragment: Fragment() {

    var projectPagerAdapter: CostPagerAdapter? = null

    private lateinit var viewPagerProject: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cost_list_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewPagerProject = view.findViewById(R.id.view_pager_project1)
        tabLayout = view.findViewById(R.id.tab_layout1)

        projectPagerAdapter = CostPagerAdapter(requireActivity())
        viewPagerProject.adapter = projectPagerAdapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerProject.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPagerProject.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)!!.select()
            }
        })
    }
}