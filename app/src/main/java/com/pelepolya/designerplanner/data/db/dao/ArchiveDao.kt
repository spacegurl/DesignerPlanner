package com.pelepolya.designerplanner.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pelepolya.designerplanner.data.db.models.Archive
import com.pelepolya.designerplanner.domain.entity.ProjectNote

@Dao
interface ArchiveDao {

    @Query("SELECT * FROM Archive")
    fun getArchiveList(): LiveData<List<ProjectNote>>

    @Insert
    fun inertArchive(archive: Archive)

    @Query("DELETE FROM Archive WHERE id = :id")
    fun deleteArchive(id: Int)
}