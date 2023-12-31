package com.example.sextouapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.sextouapp.adapters.EventItem
import com.example.sextouapp.dao.EventDao

class MainActivity : AppCompatActivity() {
    lateinit var database: Database
    lateinit var sqlOpen: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true);
        supportActionBar?.setTitle("Sextou!");
        supportActionBar?.setSubtitle("Ache seu rolê")

        val listView: ListView = findViewById(R.id.events_list)
        val events = EventDao(baseContext).getAll()
        val eventItemAdapter = EventItem(this, events)
        listView.adapter = eventItemAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val i = Intent(baseContext, EventActivity::class.java)

            i.apply {
                putExtra("eventId", events[position].id)
            }

            startActivity(i)
        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            AlertDialog.Builder(this)
                .setTitle("Deseja remover esse evento?")
                .setPositiveButton(
                    "Sim"
                ) { _, _ ->
                    EventDao(baseContext).delete(events[position].id)
                    eventItemAdapter.notifyDataSetChanged()
                    startActivity(Intent(baseContext, MainActivity::class.java))
                }
                .setNegativeButton("Voltar") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_event -> {
                Log.i("SextouApp", "Criar evento");
                startActivity(Intent(baseContext, NewEventActivity::class.java))
                true
            }

            R.id.reservations -> {
                Log.i("SextouApp", "Reservas");
                startActivity(Intent(baseContext, ReservationActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}