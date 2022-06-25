package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.movieapp.viewmodels.moviesFragment.MoviesScreenViewModel
import com.example.movieapp.viewmodels.searchFragment.SearchViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private val viewModelPremieres : MoviesScreenViewModel by viewModels()
    private val viewModelSearch : SearchViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?)  {
        setTheme(R.style.Theme_MovieApp)
        Log.e("Activities", "MainActivity launched")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (viewModelPremieres.randomFilm.value == null){
            viewModelPremieres.getRandomFilm()
        }
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    navController.navigate(R.id.moviesFragment)
                    true
                }
                R.id.liked_page -> {

                    true
                }
                R.id.search_page -> {
                    navController.navigate(R.id.searchFragment)
                    //supportFragmentManager.beginTransaction().replace(R.id.navHostFragment, searchFragment).commit()
                    true
                }

                else -> {false}
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}