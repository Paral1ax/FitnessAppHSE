package com.mir.fitnessapplication.entry.ui.register.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseURL {
    companion object {
        var database: FirebaseDatabase? = null
        var databaseReference: DatabaseReference? = null
        var firebaseAuth: FirebaseAuth? = null
        const val DATABASE_URL = "https://fitnessapplication-88d7b-default-rtdb.europe-west1.firebasedatabase.app"
        const val FOLDER_PROFILE_IMAGE = "profile_image"

        init {
            database = FirebaseDatabase.getInstance(DATABASE_URL)
            databaseReference = database!!.getReference("UserData")
            firebaseAuth = FirebaseAuth.getInstance()
        }


    }
}