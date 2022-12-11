package com.example.movieapp.di

import androidx.room.Room
import com.example.data.AppDatabase
import com.example.movieapp.AppApplication
import com.example.movieapp.fragments.FavouritesFragment
import com.example.movieapp.fragments.abstractions.FilmDetailsAbstractFragment
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.koinApplication
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