package com.example.sextouapp.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sextouapp.R
import com.example.sextouapp.dao.models.Event

class EventItem(
    private val context: Activity,
    private val events: ArrayList<Event>
) : ArrayAdapter<Event>(
    context, R.layout.event_item, events
) {

    override fun getView(
        position: Int,
        view: View?,
        parent: ViewGroup
    ): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.event_item, null, true)

        val eventNameField = rowView.findViewById(R.id.item_name) as TextView
        val eventAddressField = rowView.findViewById(R.id.item_address) as TextView

        eventNameField.text = events[position].name
        eventAddressField.text = events[position].address

        return rowView
    }
}