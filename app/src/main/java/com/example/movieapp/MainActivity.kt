package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?)  {
        setTheme(R.style.Theme_MovieApp)
        Log.e("Activities", "MainActivity launched")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        val bottomNav : BottomNavigationView = findViewById(R.id.bottomNav)
//        bottomNav.setOnNavigationItemReselectedListener {
//            when(it.itemId){
//                R.id.home_page -> {
//                    true
//            }
//            }
//        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}