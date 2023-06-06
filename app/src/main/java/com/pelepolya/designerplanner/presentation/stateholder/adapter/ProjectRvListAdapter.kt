package com.pelepolya.designerplanner.presentation.stateholder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pelepolya.designerplanner.databinding.ItemProjectBinding
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.ProjectStatus

class ProjectRvListAdapter(
    private val projectList: List<ProjectNote>
) : RecyclerView.Adapter<ProjectRvListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemProjectBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = projectList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = projectList[position]
        with(holder) {
            if (item.status == ProjectStatus.VISIBLE) {
                binding.textProjectName.text = item.title
            }
        }
    }

}