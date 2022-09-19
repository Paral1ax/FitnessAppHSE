package com.mir.fitnessapplication.api.model

import android.provider.ContactsContract
import java.util.*

class User {
    private var id: Long = 0
    private lateinit var email: String
    private lateinit var token: String
    private lateinit var name: String
    private lateinit var surname: String
    private lateinit var birthDate: Date
    private var height = 0
    private var weight = 0
    private var sex = "Male"
    private lateinit var phone: String



}