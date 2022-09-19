package com.mir.fitnessapplication.main.ui.home.calendar

import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.maps.MapView
import com.mir.fitnessapplication.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

open class CalendarView(context: Context?) : LinearLayout(context) {


    lateinit var gridAdapter: CalendarGridAdapter
    lateinit var prev: ImageButton
    lateinit var next: ImageButton
    lateinit var currDate: TextView
    lateinit var gridView: GridView
    val MAX_CALENDAR_DAYS = 42
    private val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH)

    lateinit var alertDialog: AlertDialog
    private var dates: MutableList<Date> = ArrayList()
    private var events: MutableList<CalendarEvent> = ArrayList()

    val dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)

    constructor(context: Context?, attrs: AttributeSet?) : this(context) {
        initialiseLayout()
        setUpCalendar()
        prev.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            setUpCalendar()
        }

        next.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            setUpCalendar()
        }
        gridView.setOnItemClickListener { parent, view, position, id ->
            val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
            builder.setCancelable(true)
            val addView = LayoutInflater.from(parent.context).inflate(R.layout.add_new_event_layout, null)
            val eventName: EditText = addView.findViewById(R.id.calendar_event_textview)
            val eventTime: TextView = addView.findViewById(R.id.calendar_event_show_time)
            val timeButton: ImageButton = addView.findViewById(R.id.calendar_event_time_button)
            val comment: EditText = addView.findViewById(R.id.calendar_commentary_textview)
            val addEvent: Button = addView.findViewById(R.id.calendar_event_add_event_button)
            val mapView: MapView = addView.findViewById(R.id.mapView)
            val place: TextView = addView.findViewById(R.id.calendar_event_place)

            timeButton.setOnClickListener {
                val calendar: Calendar = Calendar.getInstance()
                val hours = calendar.get(Calendar.HOUR_OF_DAY)
                val minutes = calendar.get(Calendar.MINUTE)
                val timePickerDialog: TimePickerDialog = TimePickerDialog(addView.context, R.style.Theme_AppCompat_Dialog,
                TimePickerDialog.OnTimeSetListener{
                    view, hoursOfDay, min ->
                    run {
                        val c = Calendar.getInstance()
                        c.set(Calendar.HOUR_OF_DAY, hoursOfDay)
                        c.set(Calendar.MINUTE, min)
                        c.timeZone = TimeZone.getDefault()
                        val hFormat: SimpleDateFormat = SimpleDateFormat("k:mm", Locale.ENGLISH)
                        val eventT = hFormat.format(c.time)
                        eventTime.text = eventT
                    }

                }, hours, minutes, true)
                timePickerDialog.show()
            }

            addEvent.setOnClickListener {
                setUpCalendar()
                alertDialog.dismiss()
            }

            builder.setView(addView)
            alertDialog = builder.create()
            alertDialog.show()
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): this(context)

    private fun initialiseLayout() {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.calendar_layout, this)
        prev = view.findViewById(R.id.calendar_month_before)
        next = view.findViewById(R.id.calendar_next_month)
        currDate = view.findViewById(R.id.calendar_current_date)
        gridView = view.findViewById(R.id.homefragmentGridview)
    }

    private fun setUpCalendar() {
        currDate.text = dateFormat.format(calendar.time)
        dates.clear()
        val monthCalendar = calendar.clone() as Calendar
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth)

        while (dates.size < MAX_CALENDAR_DAYS) {
            dates.add(monthCalendar.time)
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)

        }

        gridAdapter = CalendarGridAdapter(context, dates, calendar, events)
        gridView.adapter = gridAdapter
    }

    private fun collectEvents() {

    }

    private fun saveEvent() {

    }
}