package com.example.sextouapp.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sextouapp.R
import com.example.sextouapp.dao.EventDao
import com.example.sextouapp.dao.ReservationDao
import com.example.sextouapp.dao.models.Event
import com.example.sextouapp.dao.models.Reservation

class ReservationItem(
    private val context: Activity,
    private val reservations: ArrayList<Reservation>
) : ArrayAdapter<Reservation>(
    context, R.layout.reservation_item, reservations
) {

    override fun getView(
        position: Int,
        view: View?,
        parent: ViewGroup
    ): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.reservation_item, null, true)

        val reservationIdView = rowView.findViewById(R.id.reservationIdView) as TextView
        val participantsCountView = rowView.findViewById(R.id.participantsCountView) as TextView
        val eventNameView = rowView.findViewById(R.id.eventNameView) as TextView

        reservationIdView.text = reservations[position].id.toString()
        participantsCountView.text = ReservationDao(context).getParticipantsCount(reservations[position].id).toString()
        eventNameView.text = EventDao(context).getEventName(reservations[position].eventId)

        return rowView
    }
}