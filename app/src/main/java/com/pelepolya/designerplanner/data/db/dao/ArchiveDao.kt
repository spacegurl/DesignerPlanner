package com.pelepolya.designerplanner.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.pelepolya.designerplanner.data.db.models.Archive

@Dao
interface ArchiveDao {

    @Insert
    fun inertArchive(archive: Archive)
}