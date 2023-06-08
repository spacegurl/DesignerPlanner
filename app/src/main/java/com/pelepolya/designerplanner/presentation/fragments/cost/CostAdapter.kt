package com.pelepolya.designerplanner.presentation.fragments.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pelepolya.designerplanner.databinding.FragmentCostListRvLayoutBinding

class CostAdapter(
    private val cells: Int
) : RecyclerView.Adapter<CostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentCostListRvLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = FragmentCostListRvLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = cells

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}