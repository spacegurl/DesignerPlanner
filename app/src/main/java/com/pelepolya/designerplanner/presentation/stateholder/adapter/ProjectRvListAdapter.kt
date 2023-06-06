package com.pelepolya.designerplanner.presentation.stateholder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pelepolya.designerplanner.databinding.ItemProjectBinding
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.ProjectStatus
import com.pelepolya.designerplanner.domain.entity.Status

class ProjectRvListAdapter : RecyclerView.Adapter<ProjectRvListAdapter.ViewHolder>() {

    var projectList = listOf<ProjectNote>()
        set(value) {
            val callback = ProjectListDiffCallback(projectList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var onProjectListClickListener: ((Int, String) -> Unit)? = null

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
            if (item.status == Status.VISIBLE) {
                binding.textProjectName.text = item.title
                binding.root.setOnClickListener {
                    onProjectListClickListener?.invoke(position, item.title)
                }
            }
        }
    }
}