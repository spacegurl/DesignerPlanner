package com.pelepolya.designerplanner.presentation.fragments.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.presentation.stateholder.adapter.ProjectPagerAdapter

class ProjectTabLayoutFragment : Fragment() {
    var projectPagerAdapter: ProjectPagerAdapter? = null

    private lateinit var viewPagerProject: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val args by navArgs<ProjectTabLayoutFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_project_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = view.findViewById<TextView>(R.id.project_name)
        text.text = args.projectName

        viewPagerProject = view.findViewById(R.id.view_pager_project)
        tabLayout = view.findViewById(R.id.tab_layout)

        projectPagerAdapter = ProjectPagerAdapter(requireActivity(), args.projectPosition)
        viewPagerProject.adapter = projectPagerAdapter
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerProject.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPagerProject.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)!!.select()
            }
        })
    }
}