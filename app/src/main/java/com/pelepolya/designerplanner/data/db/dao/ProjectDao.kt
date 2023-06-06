package com.pelepolya.designerplanner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pelepolya.designerplanner.data.db.models.Project

@Dao
interface ProjectDao {

    @Query("SELECT * FROM Project")
    fun getProjectList(): LiveData<List<Project>>

    @Insert
    fun inertProject(project: Project)
}