package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.appModule
import com.example.movieapp.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppApplication)
            modules(listOf(appModule, dataModule))
        }
    }
}