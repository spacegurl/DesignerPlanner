package com.pelepolya.designerplanner.presentation.fragments.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pelepolya.designerplanner.R
import com.pelepolya.designerplanner.databinding.FragmentCostListBinding

class CostListFragment : Fragment() {

    private var _binding: FragmentCostListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.patternButton.setOnClickListener {
//            val action = CostListFragmentDirections.actionCostListFragmentToCostListTabLayoutFragment()
            findNavController().navigate(R.id.action_costListFragment_to_costListTabLayoutFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}