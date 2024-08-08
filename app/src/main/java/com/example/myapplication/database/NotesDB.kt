package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.Character
import com.example.myapplication.data.Idea
import com.example.myapplication.data.Location
import com.example.myapplication.data.Scene

@Database(entities = [Idea::class, Scene::class, Character::class, Location::class], version = 1, exportSchema = false)
abstract class NotesDB: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getInstance(context: Context): NotesDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDB::class.java,
                        "notes"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}