package com.pelepolya.designerplanner.domain.use_case.auth

import com.pelepolya.designerplanner.domain.repository.Repository

class LogOutUseCase(
    private val repository: Repository
) {

    operator fun invoke(phone: String) {
        repository.logOutUseCase(phone = phone)
    }
}
