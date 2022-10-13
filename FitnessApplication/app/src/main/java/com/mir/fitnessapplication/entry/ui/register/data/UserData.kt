package com.mir.fitnessapplication.entry.ui.register.data

import java.util.*

class UserData() {

    lateinit var username: String
    lateinit var name: String

    constructor(username:String, NameAndSurname: String) : this() {
        this.username = username
        this.name = NameAndSurname
    }
}