package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
}