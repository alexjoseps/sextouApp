package com.example.sextouapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sextouapp.dao.models.Category
import com.example.sextouapp.dao.models.Event
import com.example.sextouapp.dao.models.Participant
import com.example.sextouapp.dao.models.Reservation

class Database(context: Context?) :
    SQLiteOpenHelper( context, "sextou_app", null, 1 ) {

    init {
        Log.d("SextouApp", "Database init()")
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d("SextouApp", "Database onCreate() " + db.version.toString())

        db.execSQL(Category.SQL_CREATE)
        db.execSQL(Event.SQL_CREATE)
        db.execSQL(Reservation.SQL_CREATE)
        db.execSQL(Participant.SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("SextouApp", "Database onUpgrade from $oldVersion to $newVersion")
    }

}