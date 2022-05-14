package com.example.movieapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        }
    val api:KinoPoiskAPI by lazy {
        retrofit.create(KinoPoiskAPI::class.java)
    }
    }
