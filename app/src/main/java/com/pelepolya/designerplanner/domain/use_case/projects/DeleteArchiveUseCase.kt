package com.pelepolya.designerplanner.domain.use_case.projects

import com.pelepolya.designerplanner.domain.repository.Repository

class DeleteArchiveUseCase(
    private val repository: Repository
) {

    operator fun invoke(id: Int) {
        repository.deleteArchiveUseCase(id = id)
    }
}