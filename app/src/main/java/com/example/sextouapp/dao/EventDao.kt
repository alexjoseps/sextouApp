package com.example.sextouapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sextouapp.Database

class EventDao (context: Context? )
{
    private lateinit var sqlOpen: SQLiteDatabase
    private var database: Database

    init {
        Log.d("SextouApp", "EventDao init()")
        database = Database(context)
    }
}