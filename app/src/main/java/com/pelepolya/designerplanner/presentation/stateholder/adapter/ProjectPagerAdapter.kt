package com.pelepolya.designerplanner.presentation.stateholder.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pelepolya.designerplanner.presentation.fragments.cost.CostListTableLayoutFragment
import com.pelepolya.designerplanner.presentation.fragments.project.ProjectAlbumFragment
import com.pelepolya.designerplanner.presentation.fragments.project.ProjectCostListFragment
import com.pelepolya.designerplanner.presentation.fragments.project.ProjectNotesFragment

class ProjectPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val projectPosition: Int
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProjectNotesFragment.newInstance(projectPosition)
            1 -> CostListTableLayoutFragment()
            2 -> ProjectAlbumFragment()
            else -> ProjectNotesFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}