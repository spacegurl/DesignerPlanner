package com.pelepolya.designerplanner.data.db.mapper

import com.pelepolya.designerplanner.data.db.models.Project
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.ProjectStatus

object ProjectMapper {
    fun mapDbModelListToEntityList(it: List<Project>): List<ProjectNote> {
        val list = mutableListOf<ProjectNote>()
        for (el in it) {
            list.add(mapDbModelToEntity(el))
        }
        return list
    }

    fun mapDbModelToEntity(el: Project): ProjectNote {
        return ProjectNote(
            el.id,
            el.title,
            el.body,
            ProjectStatus.valueOf(el.status),
        )
    }

    fun mapEntityToDbModel(el: ProjectNote): Project {
        return Project(
            0,
            el.title,
            el.body,
            el.status.name,
        )
    }
}