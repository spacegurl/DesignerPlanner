package com.pelepolya.designerplanner.domain.use_case.auth

import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.repository.Repository

class AdminSignInUseCase(
    private val repository: Repository
) {

    operator fun invoke(signInUser: SignInUser) : LiveData<Boolean> {
        return repository.adminSignInUseCase(signInUser = signInUser)
    }
}
