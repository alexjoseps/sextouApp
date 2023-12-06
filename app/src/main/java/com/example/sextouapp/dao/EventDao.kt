package com.example.sextouapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sextouapp.Database
import com.example.sextouapp.dao.models.Category
import com.example.sextouapp.dao.models.Event

class EventDao(context: Context?) {
    private lateinit var sqlOpen: SQLiteDatabase
    private var database: Database

    init {
        Log.d("SextouApp", "EventDao init()")
        database = Database(context)
    }

    fun getAll(): ArrayList<Event> {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Event.TABLE,
            null,
            null,
            null,
            null,
            null,
            null
        );

        val events = ArrayList<Event>()
        while (cursor.moveToNext()) {
            val event = Event(
                cursor.getString(cursor.getColumnIndexOrThrow(Event.NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Event.ADDRESS)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Event.CATEGORY_ID))
            )

            events.add(event)
        }

        return events
    }
}