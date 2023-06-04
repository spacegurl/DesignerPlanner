package com.pelepolya.designerplanner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DealerPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DealerInfoFragment()
            1 -> DealerTableFragment()
            else -> DealerInfoFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}