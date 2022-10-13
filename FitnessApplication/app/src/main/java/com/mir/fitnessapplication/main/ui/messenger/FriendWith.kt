package com.mir.fitnessapplication.main.ui.messenger

class FriendWith() {

    lateinit var uid: String
    private var isCoach: Boolean = false
    constructor(uid: String, isCoach: Boolean) : this() {
        this.uid = uid
        this.isCoach = isCoach
    }
}