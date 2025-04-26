package com.example.stressleveltester.sresstester

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StressTestEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stressTestDao(): StressTestDao
}

object DatabaseProvider {
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "stress_app_db"
        ).build()
    }
}
