package com.pelepolya.designerplanner.domain.use_case.auth

import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.entity.SignUpUser
import com.pelepolya.designerplanner.domain.repository.Repository

class SignUpUseCase(
    private val repository: Repository
) {

    operator fun invoke(signUpUser: SignUpUser) {
        repository.signUpUseCase(signUpUser = signUpUser)
    }
}
