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
import androidx.appcompat.widget.Toolbar
import com.example.sextouapp.dao.ParticipantDao

class ParticipantActivity : AppCompatActivity() {
    lateinit var database: Database
    lateinit var sqlOpen: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participant)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true);
        supportActionBar?.setTitle("Sextou!");
        supportActionBar?.setSubtitle("Ache seu rolê")

        val listView: ListView = findViewById(R.id.participants_list)
        val participants =
            ParticipantDao(baseContext).getAll(intent.getIntExtra("reservationId", 0))
                .map { it.name }
        val participantItemAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, participants)
        listView.adapter = participantItemAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.participant_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.back_to_event -> {
                Log.i("SextouApp", "Voltar para eventos");
                startActivity(Intent(baseContext, MainActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}