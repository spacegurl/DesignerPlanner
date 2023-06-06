package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.project

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.FieldPosition

class ProjectNotesViewModelFactory(
    private val application: Application,
    private val projectPosition:  Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectNotesViewModel::class.java)) {
            return ProjectNotesViewModel(application, projectPosition) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}