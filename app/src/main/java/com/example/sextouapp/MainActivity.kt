package com.example.sextouapp

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

        val listView : ListView = findViewById(R.id.events_list)
        val minhaLista = ArrayList<String>()
        minhaLista.add("Balburdia");
        minhaLista.add("Trezze");
        minhaLista.add("Don Pub");
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, minhaLista)
        listView.adapter = adapter

        database = Database(baseContext)
        sqlOpen = database.writableDatabase
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_item -> {
                true
            }
            R.id.events_item -> {
                Log.i("Alex", "Eventos");
                true
            }
            R.id.categories_item -> {
                Log.i(null, "Categorias");
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}