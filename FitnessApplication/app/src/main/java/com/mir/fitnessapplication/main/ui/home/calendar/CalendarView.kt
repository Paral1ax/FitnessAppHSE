package com.mir.fitnessapplication.main.ui.home.calendar

import android.app.TimePickerDialog
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.MapView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.entry.ui.register.data.UserData
import com.mir.fitnessapplication.main.ui.messenger.friends.ShowFriend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Year
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

    var auth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance(FirebaseURL.DATABASE_URL)
    var databaseReference = database!!.getReference("UserEvent")

    val dateFormat: SimpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    val monthFormat: SimpleDateFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    val yearFormat: SimpleDateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
    val eventFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

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

            val date = eventFormat.format(dates[position])
            val month = monthFormat.format(dates[position])
            val year = yearFormat.format(dates[position])

            addEvent.setOnClickListener {
                val event = CalendarEvent(eventName.text.toString(),eventTime.text.toString(),date, month, year, place.text.toString(), comment.text.toString(), "Я")
                saveEvent(event)
                setUpCalendar()
                alertDialog.dismiss()
            }

            builder.setView(addView)
            alertDialog = builder.create()
            alertDialog.show()
        }



        gridView.setOnItemLongClickListener { parent, view, position, id ->
            val date = eventFormat.format(dates[position])
            val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
            builder.setCancelable(true)
            val showView: View = LayoutInflater.from(parent.context).inflate(R.layout.calendar_event_recyclerview, null)

            val eventShowRecycler: RecyclerView = showView.findViewById(R.id.calendar_event_recyclerview)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(showView.context)
            eventShowRecycler.layoutManager = layoutManager
            eventShowRecycler.setHasFixedSize(true)

            //val adapter: EventsRecyclerAdapter = EventsRecyclerAdapter(collectEventsByDate(),showView.context)
            return@setOnItemLongClickListener true
        }
    }

    private fun collectEventsByDate(date: String) {
        val events = ArrayList<CalendarEvent>()
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
        CoroutineScope(Dispatchers.IO).launch {
            collectEventsPerMonth(monthFormat.format(calendar.time), yearFormat.format(calendar.time))
            while (dates.size < MAX_CALENDAR_DAYS) {
                dates.add(monthCalendar.time)
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
                val act = context as AppCompatActivity
                act.runOnUiThread {
                    gridAdapter = CalendarGridAdapter(context, dates, calendar, events)
                    gridView.adapter = gridAdapter
                }
            }
        }

    }

    private fun collectEventsPerMonth(month: String, year: String) {
        events.clear()
        var isComplete = false
        val convertedMonth = convertStringToMonthNum(month)
        val reference = database.getReference("UserEvent")
        val valueEventListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (users in snapshot.children) {
                    if (isComplete)
                        break
                    if (users.key == auth.uid) {
                        for (date in users.children) {
                            val mnth = date.key!!.split('-')[1]
                            val yr = date.key!!.split('-')[0]

                            if (convertedMonth.toString() == mnth && year == yr) {
                                for (time in date.children) {

                                    val event = time.getValue(CalendarEvent::class.java)
                                    events.add(event!!)
                                }
                            }
                        }
                    }
                }
                isComplete = true
                Log.d("Events", events.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        reference.addValueEventListener(valueEventListener)
    }

    private fun saveEvent(event: CalendarEvent) {
        if (!TextUtils.isEmpty(event.event) && !TextUtils.isEmpty(event.time)) {
            database.getReference("UserEvent")
                .child(FirebaseAuth.getInstance().currentUser!!.uid).child(event.date)
                .child(event.time).setValue(event)
                .addOnCompleteListener {
                    Toast.makeText(this.context, "Успешно сохранено", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun convertStringToMonthNum(month: String): Int {
        val mnth = when (month) {
            "January" -> 1
            "February" -> 2
            "March" -> 3
            "April" -> 4
            "May" -> 5
            "June" -> 6
            "July" -> 7
            "August" -> 8
            "September" -> 9
            "October" -> 10
            "November" -> 11
            "December" -> 12
            else -> 1
        }
        return mnth
    }
}