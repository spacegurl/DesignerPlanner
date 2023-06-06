package com.pelepolya.designerplanner.presentation.fragments.dealer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.pelepolya.designerplanner.databinding.FragmentDealerTabLayoutBinding
import com.pelepolya.designerplanner.presentation.stateholder.adapter.DealerPagerAdapter

class DealerTabLayoutFragment : Fragment() {
    private var binding: FragmentDealerTabLayoutBinding? = null
    var dealerPagerAdapter: DealerPagerAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDealerTabLayoutBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dealerPagerAdapter = DealerPagerAdapter(requireActivity())
        binding!!.viewPagerDealer.adapter = dealerPagerAdapter
        binding!!.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding!!.viewPagerDealer.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding!!.viewPagerDealer.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding!!.tabLayout.getTabAt(position)!!.select()
            }
        })
    }
}