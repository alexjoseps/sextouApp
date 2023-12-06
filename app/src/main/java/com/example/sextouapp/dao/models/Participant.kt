package com.example.sextouapp.dao.models

class Participant {
    companion object {
        const val TABLE = "participants"
        const val ID = "id"
        const val NAME = "name"
        const val DOCUMENT = "document"
        const val RESERVATION_ID = "reservation_id"

        val SQL_CREATE = "CREATE TABLE $TABLE ( " +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NAME TEXT NOT NULL, " +
                "$DOCUMENT TEXT NOT NULL UNIQUE, " +
                "$RESERVATION_ID INTEGER NOT NULL, " +
                "FOREIGN KEY($RESERVATION_ID) REFERENCES ${Reservation.TABLE} (${Reservation.ID}));";
    }
}