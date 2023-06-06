package com.pelepolya.designerplanner.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.pelepolya.designerplanner.data.db.DesignerPlannerDataBase
import com.pelepolya.designerplanner.data.db.mapper.ProjectMapper
import com.pelepolya.designerplanner.data.db.mapper.UserMapper
import com.pelepolya.designerplanner.data.db.models.Archive
import com.pelepolya.designerplanner.data.db.models.ProjectAdmin
import com.pelepolya.designerplanner.domain.entity.ProjectNote
import com.pelepolya.designerplanner.domain.entity.SignInUser
import com.pelepolya.designerplanner.domain.entity.SignUpUser
import com.pelepolya.designerplanner.domain.repository.Repository

class RepositoryImpl(
    private val context: Context
) : Repository {

    private val db = DesignerPlannerDataBase.getDatabase(context)
    private val userDao = db.userDao()
    private val projectDao = db.projectDao()
    private val archiveDao = db.archiveDao()
    private val projectAdminDao = db.projectAdminDao()


    override fun signInUseCase(signInUser: SignInUser): LiveData<Boolean> {
        return userDao.checkSignInValid(signInUser.phone, signInUser.password)
    }

    override fun signUpUseCase(signUpUser: SignUpUser) {
        db.queryExecutor.execute {
            userDao.insertUser(UserMapper.mapEntityToDbModel(signUpUser))
        }
    }

    override fun adminSignInUseCase(signInUser: SignInUser): LiveData<Boolean> {
        return userDao.checkAdminSignInValid(signInUser.phone, signInUser.password)
    }

    override fun logOutUseCase(phone: String) {
        TODO("Not yet implemented")
    }

    override fun addProjectUseCase(projectNote: ProjectNote) {
        db.queryExecutor.execute {
            projectDao.inertProject(ProjectMapper.mapEntityToDbModel(projectNote))
        }
    }

    override fun deleteProjectUseCase(id: Int) {
        db.queryExecutor.execute {
            val item = projectDao.getProjectById(id)
            archiveDao.inertArchive(
                Archive(
                    item.id,
                    item.title,
                    item.body,
                    item.status
                )
            )
            projectDao.deleteProject(id)
        }
    }

    override fun deleteArchiveUseCase(id: Int) {
        db.queryExecutor.execute {
            val item = archiveDao.getArchiveById(id)
            projectAdminDao.inertProject(
                ProjectAdmin(
                    item.id,
                    item.title,
                    item.body,
                    item.status
                )
            )
            archiveDao.deleteArchive(id)
        }
    }

    override fun getArchiveListUseCase(): LiveData<List<ProjectNote>> {
        return db.archiveDao().getArchiveList()
    }

    override fun getAdminProjectsListUseCase(): LiveData<List<ProjectNote>> {
        return db.projectAdminDao().getProjectList()
    }

    override fun loadProjectNoteUseCase(id: Int): LiveData<String> {
        TODO("Not yet implemented")
    }

    override fun saveProjectNoteUseCase(id: Int, noteBody: String) {
        db.queryExecutor.execute {
            projectDao.editNoteById(id, noteBody)
        }
    }

    override fun getProjectList(): LiveData<List<ProjectNote>> {
        return MediatorLiveData<List<ProjectNote>>().apply {
            addSource(projectDao.getProjectList()) {
                value = ProjectMapper.mapDbModelListToEntityList(it)
            }
        }
    }
}