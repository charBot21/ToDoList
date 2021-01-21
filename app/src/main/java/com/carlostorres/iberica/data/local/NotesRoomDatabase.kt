package com.carlostorres.iberica.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carlostorres.iberica.data.local.dao.NotsDao
import com.carlostorres.iberica.data.local.entity.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesRoomDatabase: RoomDatabase() {

    abstract fun notesDado(): NotsDao

    companion object {
        @Volatile
        private var INSTANCE: NotesRoomDatabase ?= null

        fun getDatabase(context: Context): NotesRoomDatabase {
            val tmpInstance = INSTANCE

            if ( tmpInstance != null ) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesRoomDatabase::class.java,
                    "notes_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}