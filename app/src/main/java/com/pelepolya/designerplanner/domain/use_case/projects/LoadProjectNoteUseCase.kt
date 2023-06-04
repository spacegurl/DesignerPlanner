package com.pelepolya.designerplanner.domain.use_case.projects

import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.domain.repository.Repository

class LoadProjectNoteUseCase(
    private val repository: Repository
) {

    operator fun invoke(id: Int): LiveData<String> {
        return repository.loadProjectNoteUseCase(id = id)
    }
}
