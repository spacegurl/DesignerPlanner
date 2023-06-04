package com.pelepolya.designerplanner.data.db.mapper

import com.pelepolya.designerplanner.data.db.models.User
import com.pelepolya.designerplanner.domain.entity.SignUpUser

object UserMapper {

    fun mapEntityToDbModel(signUpUser: SignUpUser): User {
        return User(
            uid = 0,
            phone = signUpUser.phone,
            fullName = signUpUser.fullName,
            password = signUpUser.password
        )
    }
}