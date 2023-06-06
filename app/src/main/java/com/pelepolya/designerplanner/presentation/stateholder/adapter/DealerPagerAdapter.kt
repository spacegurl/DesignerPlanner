package com.pelepolya.designerplanner.presentation.stateholder.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pelepolya.designerplanner.presentation.fragments.dealer.DealerInfoFragment
import com.pelepolya.designerplanner.presentation.fragments.dealer.DealerTableFragment

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