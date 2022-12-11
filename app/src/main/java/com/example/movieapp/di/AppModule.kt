package com.example.movieapp.di

import com.example.movieapp.api.KinoPoiskAPI
import com.example.movieapp.api.ResultCallAdapterFactory
import com.example.movieapp.repository.Repository
import com.example.movieapp.repository.RepositoryImpl
import com.example.movieapp.viewmodels.ActorViewModel
import com.example.movieapp.viewmodels.FavouritesViewModel
import com.example.movieapp.viewmodels.detailsFragment.DetailsFragmentViewModel
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViewModel
import com.example.movieapp.viewmodels.searchFragment.SearchViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module{
    val gson = GsonBuilder()
        .setLenient()
        .create()
    single{
        Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
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

    viewModel{
        DetailsFragmentViewModel(get(), filmDao = get())
    }

    viewModel{
        FavouritesViewModel(get())
    }

    viewModel{
        SearchViewModel(get())
    }

    viewModel{
        ActorViewModel(get())
    }
}