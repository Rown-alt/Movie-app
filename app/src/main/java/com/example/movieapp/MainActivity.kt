package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViemModelFactory
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViewModel
import com.example.movieapp.viewmodels.series.MovieByIdViewModel
import com.example.movieapp.viewmodels.series.MovieByIdViewModelFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModelPremieres : MoviesScreenViewModel by viewModels{ MoviesScreenViemModelFactory() }
    private val viewModelSeries : MovieByIdViewModel by viewModels{ MovieByIdViewModelFactory() }
    override fun onCreate(savedInstanceState: Bundle?)  {
        setTheme(R.style.Theme_MovieApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelSeries.getSeries()

        viewModelPremieres.getRandomFilm()
        viewModelPremieres.getTop("TOP_250_BEST_FILMS")
        viewModelPremieres.getPremieres(Random.nextInt(1995, 2021),"MAY")

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}