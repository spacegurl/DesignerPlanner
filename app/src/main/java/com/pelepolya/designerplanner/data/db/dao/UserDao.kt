package com.pelepolya.designerplanner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pelepolya.designerplanner.data.db.models.User
import com.pelepolya.designerplanner.data.db.models.UserRoles
import com.pelepolya.designerplanner.domain.entity.SignInUser

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query(
        "SELECT EXISTS(SELECT * FROM User WHERE phone=:phone AND password=:password AND role=:role)"
    )
    fun checkSignInValid(
        phone: String,
        password: String,
        role: String = UserRoles.USER.name
    ): LiveData<Boolean>

    @Query(
        "SELECT EXISTS(SELECT * FROM User WHERE phone=:phone AND password=:password AND role=:role)"
    )
    fun checkAdminSignInValid(
        phone: String,
        password: String,
        role: String = UserRoles.ADMIN.name
    ): LiveData<Boolean>
}