package com.pelepolya.designerplanner.domain.use_case.projects

import com.pelepolya.designerplanner.domain.repository.Repository

class DeleteProjectUseCase(
    private val repository: Repository
) {

    operator fun invoke(id: Int) {
        repository.deleteProjectUseCase(id = id)
    }
}
