package com.mir.fitnessapplication.entry.ui.register.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Exclude
import com.google.firebase.database.ValueEventListener

class UserData() {

    lateinit var username: String
    lateinit var name: String
    lateinit var profilePicture: String
    lateinit var phoneNumber: String
    lateinit var state: String

    constructor(username: String, NameAndSurname: String, profile: String, phone: String, state: String) : this() {
        this.username = username
        this.name = NameAndSurname
        this.profilePicture = profile
        this.phoneNumber = phone
        this.state = state
    }

    @Exclude
    private fun getCurrentUserData(): UserData? {
        val databaseReference = FirebaseURL.database!!.getReference("UserData")
        val firebaseAuth = FirebaseURL.firebaseAuth
        var currUser: UserData? = null
        var isComplete = false
        val valueEventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Thread {
                    for (ds in snapshot.children) {
                        val userdata = ds.getValue(UserData::class.java)
                        val k = ds.key
                        if (k == firebaseAuth?.uid) {
                            currUser = userdata
                            isComplete = true
                        }
                    }
                }.start()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.addValueEventListener(valueEventListener)
        while (!isComplete) {

        }
        return currUser
    }

    @Exclude
    fun setCurrUserData() {
        val currentUser = getCurrentUserData()
        if (currentUser != null) {
            this.username = currentUser.username
            this.name = currentUser.name
            this.phoneNumber = currentUser.phoneNumber
            this.profilePicture = currentUser.profilePicture
        }
    }

}