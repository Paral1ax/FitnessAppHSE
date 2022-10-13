package com.mir.fitnessapplication.main.ui.messenger.friends

class ShowFriend() {

    lateinit var id: String
    lateinit var name: String
    lateinit var text: String
    var isCoach: Boolean = false

    constructor(id: String, name: String, text: String, isCoach: Boolean) : this() {
        this.id = id
        this.name = name
        this.text = text
        this.isCoach = isCoach
    }
}