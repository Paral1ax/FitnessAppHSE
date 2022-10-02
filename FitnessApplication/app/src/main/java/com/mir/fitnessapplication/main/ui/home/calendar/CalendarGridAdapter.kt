package com.mir.fitnessapplication.main.ui.home.calendar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.graphics.green
import androidx.versionedparcelable.VersionedParcel
import com.mir.fitnessapplication.R
import okhttp3.internal.format
import java.text.Format
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.zip.Inflater
import kotlin.collections.ArrayList

class CalendarGridAdapter(context: Context, private val dates: MutableList<Date>, private val currentDate: Calendar, val events: MutableList<CalendarEvent>): ArrayAdapter<Any?>(context, R.layout.single_calendar_cell) {

    private var inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val monthDate = dates[position]
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = monthDate

        val dayN: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val displayMonth = calendar.get(Calendar.MONTH) + 1
        val displayYear = calendar.get(Calendar.YEAR)
        val currentMonth = currentDate.get(Calendar.MONTH) + 1
        val currentYear = currentDate.get(Calendar.YEAR)

        var view = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.single_calendar_cell, parent, false)

        }

        if (displayMonth == currentMonth && displayYear == currentYear) {
            view!!.background = context.getDrawable(R.drawable.add_event_button)

        }
        val day: TextView = view!!.findViewById(R.id.calendar_day_cell)
        day.text = dayN.toString()
        val eventCalendar = Calendar.getInstance()
        val arrList = ArrayList<String>()
        val eventNumber: TextView = view.findViewById(R.id.events_id_textview)
        var i = 0
        while (i < events.size) {
            eventCalendar.time = convertStringToDate(events[i].date)
            if (dayN == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                && displayYear == eventCalendar.get(Calendar.YEAR)) {
                arrList.add(events[i].event)
                eventNumber.text = arrList.size.toString()
            }
            i++
        }

        return view
    }

    private fun convertStringToDate(eventDate: String): Date? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(eventDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    override fun getCount(): Int {
        return dates.size
    }

    override fun getPosition(item: Any?): Int {
        return dates.indexOf(item)
    }

    override fun getItem(position: Int): Any? {
        return dates[position]
    }
}