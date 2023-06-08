package com.pelepolya.designerplanner.presentation.fragments.cost

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CostPagerAdapter(
    fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CostListTableLayoutFragment()
            1 -> CostListTableLayoutFragment()
            2 -> CostListTableLayoutFragment()
            3 -> CostListTableLayoutFragment()
            else -> CostListTableLayoutFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}