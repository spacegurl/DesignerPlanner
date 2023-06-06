package com.pelepolya.designerplanner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pelepolya.designerplanner.data.db.models.Project
import com.pelepolya.designerplanner.data.db.models.ProjectAdmin
import com.pelepolya.designerplanner.domain.entity.ProjectNote

@Dao
interface ProjectAdminDao {

    @Query("SELECT * FROM ProjectAdmin")
    fun getProjectList(): LiveData<List<ProjectNote>>

    @Insert
    fun inertProject(projectAdmin: ProjectAdmin)

    @Query("UPDATE ProjectAdmin SET body = :body WHERE id = :id")
    fun editNoteById(id: Int, body: String)

    @Query("DELETE FROM ProjectAdmin WHERE id = :id")
    fun deleteProject(id: Int)

    @Query("SELECT * FROM ProjectAdmin WHERE id = :id")
    fun getProjectById(id: Int): Project
}