package com.example.sextouapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sextouapp.Database
import com.example.sextouapp.dao.models.Event
import com.example.sextouapp.dao.models.Participant
import com.example.sextouapp.dao.models.Reservation

class ReservationDao(context: Context?) {
    private lateinit var sqlOpen: SQLiteDatabase
    private var database: Database

    init {
        Log.d("SextouApp", "ReservationDao init()")
        database = Database(context)
    }

    fun getAll(): ArrayList<Reservation> {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Reservation.TABLE, null, null, null, null, null, null
        );

        val reservations = ArrayList<Reservation>()
        while (cursor.moveToNext()) {
            val reservation = Reservation(
                cursor.getInt(cursor.getColumnIndexOrThrow(Reservation.ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Reservation.EVENT_ID))
            )

            reservations.add(reservation)
        }
        cursor.close()

        return reservations
    }

    fun getParticipantsCount(reservationId: Int): Int {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Participant.TABLE,
            null,
            "${Participant.RESERVATION_ID} = ?",
            arrayOf(reservationId.toString()),
            null,
            null,
            null
        );

        val participantsCount = cursor.count
        cursor.close()

        return participantsCount
    }
}