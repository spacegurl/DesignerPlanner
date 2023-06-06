package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.data.RepositoryImpl
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.use_case.auth.SignInUseCase

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val signUpUseCase = SignInUseCase(repository)

    lateinit var checkAuthValid: LiveData<Boolean>

    fun validAuth(signInUser: SignInUser) {
        checkAuthValid = signUpUseCase(signInUser)
    }
}