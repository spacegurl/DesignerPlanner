package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.data.RepositoryImpl
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.use_case.auth.AdminSignInUseCase

class AdminSignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val adminSignInUseCase = AdminSignInUseCase(repository)

    lateinit var checkAuthValid: LiveData<Boolean>

    fun validAuth(signInUser: SignInUser) {
        checkAuthValid = adminSignInUseCase(signInUser)
    }

}