package com.example.sextouapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.example.sextouapp.adapters.EventItem
import com.example.sextouapp.adapters.ReservationItem
import com.example.sextouapp.dao.EventDao
import com.example.sextouapp.dao.ReservationDao

class ReservationActivity : AppCompatActivity() {
    lateinit var database: Database
    lateinit var sqlOpen: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true);
        supportActionBar?.setTitle("Sextou!");
        supportActionBar?.setSubtitle("Ache seu rolê")

        database = Database(baseContext)
        sqlOpen = database.writableDatabase

        val reservationsListView: ListView = findViewById(R.id.reservations_list)
        val reservations = ReservationDao(baseContext).getAll()
        val reservationItemAdapter = ReservationItem(this, reservations)
        reservationsListView.adapter = reservationItemAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.reservation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.back_to_event -> {
                Log.i("SextouApp", "Voltar para eventos");
                    startActivity(Intent(baseContext, MainActivity::class.java))
                true
            }

            R.id.new_reservation -> {
                Log.i("SextouApp", "Criar Reserva");
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}