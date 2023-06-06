package com.pelepolya.designerplanner.presentation.stateholder.viewmodel.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pelepolya.designerplanner.data.RepositoryImpl
import com.pelepolya.designerplanner.domain.entity.SignUpUser
import com.pelepolya.designerplanner.domain.use_case.auth.SignUpUseCase

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val signUpUseCase = SignUpUseCase(repository)

    fun signUp(signUpUser: SignUpUser) {
        signUpUseCase.invoke(signUpUser)
    }
}