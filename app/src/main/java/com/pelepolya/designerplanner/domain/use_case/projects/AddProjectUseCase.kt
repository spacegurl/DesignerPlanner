package com.pelepolya.designerplanner.domain.use_case.projects

import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.repository.Repository

class AddProjectUseCase(
    private val repository: Repository
) {

    operator fun invoke(projectNote: ProjectNote) {
        repository.addProjectUseCase(projectNote = projectNote)
    }
}
