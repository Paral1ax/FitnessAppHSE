package com.mir.fitnessapplication.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.login.LoginActivity
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.ui.fitness.FitnessFragment
import com.mir.fitnessapplication.main.ui.food.FoodFragment
import com.mir.fitnessapplication.main.ui.home.HomeFragment
import com.mir.fitnessapplication.main.ui.messenger.AppStates
import com.mir.fitnessapplication.main.ui.messenger.MessengerFragment
import com.mir.fitnessapplication.main.ui.profile.ProfileFragment
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    var storageReference: StorageReference? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        storageReference = FirebaseStorage.getInstance().reference
        auth = FirebaseAuth.getInstance()

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

    override fun onStart() {
        super.onStart()
        Thread {
            val currentUser = UserData()
            currentUser.setCurrUserData()
            AppStates.updateState(AppStates.ONLINE, currentUser)
            LoginActivity.userdata = currentUser
        }.start()
    }

    private fun FragmentManager.clearBackStack() {

        for (i in 0 until backStackEntryCount) {
            popBackStack()
        }
    }

    override fun onStop() {
        super.onStop()
        LoginActivity.userdata?.let { AppStates.updateState(AppStates.OFFLINE, it) }
    }
}