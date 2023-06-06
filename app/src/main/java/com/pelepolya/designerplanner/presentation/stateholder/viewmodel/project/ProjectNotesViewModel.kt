package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pelepolya.designerplanner.data.RepositoryImpl
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.use_case.projects.GetProjectListUseCase
import com.pelepolya.designerplanner.domain.use_case.projects.SaveProjectNoteUseCase

class ProjectNotesViewModel(application: Application, private val projectPosition: Int) :
    AndroidViewModel(application) {
    private val repository = RepositoryImpl(application)

    val projectListLiveData = GetProjectListUseCase(repository).invoke()
    private val saveProjectNoteUseCase = SaveProjectNoteUseCase(repository)
    val loadProjectNoteLiveData = MutableLiveData<ProjectNote>()

    private val _bodyNoteLivedata = MutableLiveData<ProjectNote>()
    val bodyNoteLiveData
        get() = _bodyNoteLivedata

    fun loadProjectNote() {
        _bodyNoteLivedata.value = projectListLiveData.value!!.get(projectPosition)
    }

    fun saveProjectNote(id: Int, noteBody: String) {
        if (projectListLiveData.value != null) {
            saveProjectNoteUseCase.invoke(projectListLiveData.value!![projectPosition].id, noteBody)
        }
    }
}