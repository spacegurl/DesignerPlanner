package com.pelepolya.designerplanner.domain.use_case.projects

import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.repository.Repository

class GetProjectListUseCase(
    private val repository: Repository
) {

    operator fun invoke(): LiveData<List<ProjectNote>> {
        return repository.getProjectList()
    }
}