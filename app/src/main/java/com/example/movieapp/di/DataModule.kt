package com.example.movieapp.di

import androidx.room.Room
import com.example.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module{
    single {
            Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "film_database"
        ).build()
    }
    single {
        get<AppDatabase>().filmDao()
    }
}