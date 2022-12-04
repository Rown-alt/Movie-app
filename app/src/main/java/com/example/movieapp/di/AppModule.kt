package com.example.movieapp.di

import com.example.movieapp.api.KinoPoiskAPI
import com.example.movieapp.api.ResultCallAdapterFactory
import com.example.movieapp.repository.Repository
import com.example.movieapp.repository.RepositoryImpl
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module{
    single{
        Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
            .create(KinoPoiskAPI::class.java)
    }

    single<Repository>{
        RepositoryImpl(get())
    }

    viewModel{
        MoviesScreenViewModel(get())
    }
}