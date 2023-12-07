package com.example.sextouapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sextouapp.Database
import com.example.sextouapp.dao.models.Event
import com.example.sextouapp.dao.models.Participant

class ParticipantDao(context: Context?) {
    private lateinit var sqlOpen: SQLiteDatabase
    private var database: Database

    init {
        Log.d("SextouApp", "ParticipantDao init()")
        database = Database(context)
    }

    fun getAll(reservationId: Int): ArrayList<Participant> {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Participant.TABLE,
            null,
            "reservation_id = ?",
            arrayOf(reservationId.toString()),
            null,
            null,
            null
        );

        val participants = ArrayList<Participant>()
        while (cursor.moveToNext()) {
            val participant = Participant(
                cursor.getInt(cursor.getColumnIndexOrThrow(Participant.ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(Participant.NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Participant.DOCUMENT)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Participant.RESERVATION_ID))
            )

            participants.add(participant)
        }
        cursor.close()

        return participants
    }
}