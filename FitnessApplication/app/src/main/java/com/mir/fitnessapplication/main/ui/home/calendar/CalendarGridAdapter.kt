package com.mir.fitnessapplication.main.ui.home.calendar

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.graphics.green
import androidx.versionedparcelable.VersionedParcel
import com.mir.fitnessapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.internal.format
import java.io.IOException
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
        val cal: Calendar = Calendar.getInstance()
        calendar.time = monthDate
        cal.time = dates[15]
        val curMonth = cal.get(Calendar.MONTH) + 1
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
        var eventCount = ""
        while (i < events.size) {
            try {
                //CoroutineScope(Dispatchers.IO).launch {
                    eventCalendar.time = convertStringToDate(events.get(i).date)!!
                    //eventCalendar.time = convertStringToDate(events[i].date)!!
                    val mDay = eventCalendar.get(Calendar.DAY_OF_MONTH)
                    val m = eventCalendar.get(Calendar.MONTH) + 1
                    val y = eventCalendar.get(Calendar.YEAR)
                    Log.d("Events", "$mDay $m $y")
                    if (dayN == mDay && curMonth == m
                        && displayYear == y) {
                        arrList.add(events.get(i).event)
                        eventCount = "${arrList.size} соб"
                        eventNumber.text = eventCount
                    }
                    i++
                //}
            } catch (ex: IOException) {
                print(ex.message)
            }
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