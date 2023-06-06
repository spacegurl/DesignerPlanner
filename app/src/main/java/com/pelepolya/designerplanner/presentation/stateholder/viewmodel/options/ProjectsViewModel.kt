package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pelepolya.designerplanner.data.RepositoryImpl
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.ProjectStatus
import com.pelepolya.designerplanner.domain.entity.Status
import com.pelepolya.designerplanner.domain.use_case.projects.*

class ProjectsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl(application)

    val projectListLiveData = GetProjectListUseCase(repository).invoke()
    val archiveListLiveData = GetArchiveListUseCase(repository).invoke()

    private val addProjectUseCase = AddProjectUseCase(repository)
    private val deleteProjectUseCase = DeleteProjectUseCase(repository)
    private val deleteArchiveUseCase = DeleteArchiveUseCase(repository)


    fun deleteProject(id: Int) {
        deleteProjectUseCase.invoke(id)
    }

    fun deleteArchive(id: Int) {
        deleteArchiveUseCase.invoke(id)
    }

    fun addProject(projectName: String) {
        addProjectUseCase.invoke(
            projectNote = ProjectNote(
                0,
                projectName,
                "",
                Status.VISIBLE
            )
        )
    }
}
