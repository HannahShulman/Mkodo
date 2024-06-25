package com.hanna.mkodo.test.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hanna.mkodo.test.domain.models.Draw

@Database(entities = [Draw::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drawDao(): DrawDao
}