package com.pelepolya.designerplanner.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pelepolya.designerplanner.data.db.dao.UserDao
import com.pelepolya.designerplanner.data.db.models.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
abstract class DesignerPlannerDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: DesignerPlannerDataBase? = null

        fun getDatabase(context: Context): DesignerPlannerDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DesignerPlannerDataBase::class.java,
                    "designer_planner_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}