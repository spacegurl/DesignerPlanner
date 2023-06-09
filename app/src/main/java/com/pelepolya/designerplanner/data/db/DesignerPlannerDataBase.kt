package com.pelepolya.designerplanner.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pelepolya.designerplanner.data.db.dao.UserDao
import com.pelepolya.designerplanner.data.db.models.User
import com.pelepolya.designerplanner.data.db.models.UserRoles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            INSTANCE?.userDao()?.insertUser(
                                User(
                                    uid = 0,
                                    phone = "0000000000",
                                    fullName = "Admin",
                                    password = "12345",
                                    role = UserRoles.ADMIN.name
                                )
                            )
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}