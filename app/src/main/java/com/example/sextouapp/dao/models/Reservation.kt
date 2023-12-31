package com.example.sextouapp.dao.models

class Reservation(var id: Int, var eventId: Int) {
    companion object {
        const val TABLE = "reservations"
        const val ID = "id"
        const val EVENT_ID = "event_id"

        val SQL_CREATE = "CREATE TABLE $TABLE ( " +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$EVENT_ID INTEGER NOT NULL, " +
                "CONSTRAINT fk_events FOREIGN KEY($EVENT_ID) REFERENCES ${Event.TABLE} (${Event.ID}) ON DELETE CASCADE);";
    }
}