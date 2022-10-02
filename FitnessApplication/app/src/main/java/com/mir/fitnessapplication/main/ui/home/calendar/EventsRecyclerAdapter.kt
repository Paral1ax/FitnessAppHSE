package com.mir.fitnessapplication.main.ui.home.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R

class EventsRecyclerAdapter
    (var events: ArrayList<CalendarEvent>, var context: Context): RecyclerView.Adapter<EventsRecyclerAdapter.EventsViewHolder>() {


    class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName: TextView
        var time: TextView
        var place: TextView
        var comment: TextView
        var editor: TextView

        init {
            eventName = itemView.findViewById(R.id.show_event_text)
            time = itemView.findViewById(R.id.show_time_text)
            place = itemView.findViewById(R.id.show_place_textview)
            comment = itemView.findViewById(R.id.show_comment)
            editor = itemView.findViewById(R.id.show_editor_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.show_calendar_events_layout, parent, false)

        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val ev = events[position]
        holder.eventName.text = ev.event
        holder.time.text = ev.time
        holder.place.text = ev.place
        holder.comment.text = ev.comment
        holder.editor.text = ev.editor
    }

    override fun getItemCount(): Int {
        return events.size
    }
}