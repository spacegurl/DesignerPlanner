package com.pelepolya.designerplanner.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "password") val password: String
)