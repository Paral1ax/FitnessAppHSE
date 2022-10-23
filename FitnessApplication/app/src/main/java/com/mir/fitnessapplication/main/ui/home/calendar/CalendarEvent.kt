package com.mir.fitnessapplication.main.ui.home.calendar

class CalendarEvent() {
    lateinit var event:String
    lateinit var time:String
    lateinit var date:String
    lateinit var month:String
    lateinit var year:String
    lateinit var place:String
    lateinit var comment:String
    lateinit var editor: String

    constructor(event:String, time:String, date:String, month:String, year:String, place:String, comment:String, editor: String) : this() {
        this.event = event
        this.time = time
        this.date = date
        this.month = month
        this.year = year
        this.place = place
        this.comment = comment
        this.editor = editor
    }


}