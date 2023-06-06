package com.pelepolya.designerplanner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pelepolya.designerplanner.data.db.models.Project
import com.pelepolya.designerplanner.domain.entity.Status

@Dao
interface ProjectDao {

    @Query("SELECT * FROM Project")
    fun getProjectList(): LiveData<List<Project>>

    @Insert
    fun inertProject(project: Project)

    @Query("UPDATE Project SET body = :body WHERE id = :id")
    fun editNoteById(id: Int, body: String)

    @Query("DELETE FROM Project WHERE id = :id")
    fun deleteProject(id: Int)

    @Query("SELECT * FROM Project WHERE id = :id")
    fun getProjectById(id: Int): Project
}