package com.pelepolya.designerplanner.domain.repository

import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.entity.SignUpUser
import com.pelepolya.designerplanner.domain.use_case.auth.SignInUseCase
import com.pelepolya.designerplanner.domain.use_case.auth.SignUpUseCase

interface Repository {

    fun signInUseCase(signInUser: SignInUser): LiveData<Boolean>

    fun signUpUseCase(signUpUser: SignUpUser)

    fun adminSignInUseCase(signInUser: SignInUser) : LiveData<Boolean>

    fun logOutUseCase(phone: String)



    fun addProjectUseCase(projectNote: ProjectNote)

    fun deleteProjectUseCase(id: Int)

    fun loadProjectNoteUseCase(id: Int) : LiveData<String>

    fun saveProjectNoteUseCase(id: Int)
}