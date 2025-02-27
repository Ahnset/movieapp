package com.icgen.movieapp.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDB : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDB? = null

        fun getInstance(context: Context): MovieDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                    return INSTANCE as MovieDB
                }
            }

            return INSTANCE as MovieDB
        }

        private fun buildDatabase(context: Context): MovieDB =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDB::class.java,
                "movies_database"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}