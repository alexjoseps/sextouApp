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
    SQLiteOpenHelper(context, "sextou_app", null, 1) {

    init {
        Log.d("SextouApp", "Database init()")
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d("SextouApp", "Database onCreate() " + db.version.toString())

        db.execSQL(Category.SQL_CREATE)
        db.execSQL(Event.SQL_CREATE)
        db.execSQL(Reservation.SQL_CREATE)
        db.execSQL(Participant.SQL_CREATE)

        populate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d("SextouApp", "Database onUpgrade from $oldVersion to $newVersion")
    }

    private fun populate(db: SQLiteDatabase) {
        Log.d("SextouApp", "Database populate()")

        // Categories
        db.execSQL("INSERT INTO 'categories' ('name') VALUES ('Vida noturna');")
        db.execSQL("INSERT INTO 'categories' ('name') VALUES ('Restaurante');")

        // Events
        db.execSQL(
            "INSERT INTO 'events' ('name', 'address', 'category_id') VALUES " +
                    "('Noite de massas do Nico', " +
                    "'R. Antônio da Veiga, 213 - Victor Konder, Blumenau - SC, 89012-500', " +
                    "2);"
        )

        db.execSQL(
            "INSERT INTO 'events' ('name', 'address', 'category_id') VALUES " +
                    "('Cover Linkin Park', " +
                    "'R. São Paulo, 2083 - Itoupava Seca, Blumenau - SC, 89030-000', " +
                    "1);"
        )

        // Reservations
        db.execSQL("INSERT INTO 'reservations' ('event_id') VALUES (1);")

        // Participants
        db.execSQL(
            "INSERT INTO 'participants' ('name', 'document', 'reservation_id') VALUES" +
                    "('Alex Souza', '70870428071', 1);"
        )
        db.execSQL(
            "INSERT INTO 'participants' ('name', 'document', 'reservation_id') VALUES" +
                    "('Camila Paes', '32038337004', 1);"
        )
    }

}