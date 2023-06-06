package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pelepolya.designerplanner.data.RepositoryImpl
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.ProjectStatus
import com.pelepolya.designerplanner.domain.use_case.projects.AddProjectUseCase
import com.pelepolya.designerplanner.domain.use_case.projects.GetProjectListUseCase

class ProjectsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl(application)

    val projectListLiveData = GetProjectListUseCase(repository).invoke()

    private val addProjectUseCase = AddProjectUseCase(repository)

    fun addProject(projectName: String) {
        addProjectUseCase.invoke(
            projectNote = ProjectNote(
                0,
                projectName,
                "",
                ProjectStatus.VISIBLE
            )
        )
    }
}
