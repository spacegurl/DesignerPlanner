package com.pelepolya.designerplanner.presentation.fragments.cost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pelepolya.designerplanner.databinding.FragmentCostListTableLayoutBinding

class CostListTableLayoutFragment: Fragment() {

    private var _binding: FragmentCostListTableLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCostListTableLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerLayout.adapter = CostAdapter(30)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}