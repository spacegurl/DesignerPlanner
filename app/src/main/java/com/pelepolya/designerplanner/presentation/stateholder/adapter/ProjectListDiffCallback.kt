package com.pelepolya.designerplanner.presentation.stateholder.adapter

import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil
import com.pelepolya.designerplanner.domain.entity.ProjectNote

class ProjectListDiffCallback(
    private val oldList: List<ProjectNote>,
    private val newList: List<ProjectNote>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}