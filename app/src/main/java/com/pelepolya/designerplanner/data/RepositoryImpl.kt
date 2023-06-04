package com.pelepolya.designerplanner.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.pelepolya.designerplanner.data.db.DesignerPlannerDataBase
import com.pelepolya.designerplanner.data.db.mapper.UserMapper
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.entity.SignUpUser
import com.pelepolya.designerplanner.domain.repository.Repository

class RepositoryImpl(
    private val context: Context
) : Repository {

    private val db = DesignerPlannerDataBase.getDatabase(context)
    private val userDao = db.userDao()


    override fun signInUseCase(signInUser: SignInUser): LiveData<Boolean> {
        return userDao.checkSignInValid(signInUser.phone, signInUser.password)
    }

    override fun signUpUseCase(signUpUser: SignUpUser) {
        db.queryExecutor.execute {
            userDao.insertUser(UserMapper.mapEntityToDbModel(signUpUser))
        }
    }

    override fun adminSignInUseCase(signInUser: SignInUser): LiveData<Boolean> {
        TODO("Not yet implemented")
    }

    override fun logOutUseCase(phone: String) {
        TODO("Not yet implemented")
    }

    override fun addProjectUseCase(projectNote: ProjectNote) {
        TODO("Not yet implemented")
    }

    override fun deleteProjectUseCase(id: Int) {
        TODO("Not yet implemented")
    }

    override fun loadProjectNoteUseCase(id: Int): LiveData<String> {
        TODO("Not yet implemented")
    }

    override fun saveProjectNoteUseCase(id: Int) {
        TODO("Not yet implemented")
    }
}