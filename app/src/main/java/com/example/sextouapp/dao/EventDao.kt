package com.example.sextouapp.dao

import android.content.ContentValues
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

    fun findById(eventId: Int): Event {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Event.TABLE,
            null,
            "id = ?",
            arrayOf(eventId.toString()),
            null,
            null,
            null
        )

        cursor.moveToFirst()
        val event = Event(
            cursor.getInt(cursor.getColumnIndexOrThrow(Event.ID)),
            cursor.getString(cursor.getColumnIndexOrThrow(Event.NAME)),
            cursor.getString(cursor.getColumnIndexOrThrow(Event.ADDRESS)),
            cursor.getInt(cursor.getColumnIndexOrThrow(Event.CATEGORY_ID))
        )
        cursor.close()

        return event
    }

    fun getAll(): ArrayList<Event> {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Event.TABLE, null, null, null, null, null, null
        );

        val events = ArrayList<Event>()
        while (cursor.moveToNext()) {
            val event = Event(
                cursor.getInt(cursor.getColumnIndexOrThrow(Event.ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(Event.NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(Event.ADDRESS)),
                cursor.getInt(cursor.getColumnIndexOrThrow(Event.CATEGORY_ID))
            )

            events.add(event)
        }
        cursor.close()

        return events
    }

    fun update(eventId: Int, contentValues: ContentValues): Int {
        sqlOpen = database.readableDatabase
        return sqlOpen.update(Event.TABLE, contentValues, "id = ?", arrayOf(eventId.toString()))
    }

    fun delete(eventId: Int): Int {
        sqlOpen = database.readableDatabase
        return sqlOpen.delete(Event.TABLE, "id = ?", arrayOf(eventId.toString()))
    }

    fun getEventName(eventId: Int): String {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Event.TABLE,
            arrayOf("name"),
            "id = ?",
            arrayOf(eventId.toString()),
            null,
            null,
            null
        )

        cursor.moveToFirst()
        val eventName = cursor.getString(cursor.getColumnIndexOrThrow(Event.NAME))
        cursor.close()

        return eventName
    }
}