package com.mir.fitnessapplication.main.ui.messenger

import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData

enum class AppStates(val state: String) {
    ONLINE("в сети"),
    OFFLINE("был недавно"),
    TYPING("набирает сообщение");

    companion object {
        fun updateState(states: AppStates, current: UserData) {
            current.state = states.state
            FirebaseURL.database!!.reference.child("UserData").child(FirebaseURL.firebaseAuth?.uid!!).child("state")
                .setValue(states.state)
                .addOnFailureListener {

                }
        }
    }
}