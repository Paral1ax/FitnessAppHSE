package com.mir.fitnessapplication.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.FitnessFragment
import com.mir.fitnessapplication.main.ui.food.FoodFragment
import com.mir.fitnessapplication.main.ui.home.HomeFragment
import com.mir.fitnessapplication.main.ui.messenger.MessengerFragment
import com.mir.fitnessapplication.main.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

//

        navView.setOnItemSelectedListener { menuItem ->
            val fragment = when (menuItem.title) {
                getString(R.string.title_home) -> HomeFragment()
                getString(R.string.title_fitness) -> FitnessFragment()
                getString(R.string.title_messages) -> MessengerFragment()
                getString(R.string.title_food) -> FoodFragment()
                getString(R.string.title_profile) -> ProfileFragment()
                else -> HomeFragment()
            }

            supportFragmentManager.clearBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit()

            true
        }
    }

    private fun FragmentManager.clearBackStack() {

        for (i in 0 until backStackEntryCount) {
            popBackStack()
        }
    }
}