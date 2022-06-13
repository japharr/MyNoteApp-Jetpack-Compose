package com.japharr.mynoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.japharr.mynoteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(value = [DateConverter::class])
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}