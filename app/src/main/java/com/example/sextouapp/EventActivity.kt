package com.example.sextouapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.sextouapp.dao.CategoryDao

class EventActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        Log.d("SextouApp", "Clicado item spinner")
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        Log.d("SextouApp", "Não foi clidado em nada do spinner ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true);
        supportActionBar?.title = "Sextou!";
        supportActionBar?.subtitle = "Ache seu rolê"

        val eventNameInput: EditText = findViewById(R.id.eventNameInput)
        val eventAddressInput: EditText = findViewById(R.id.eventAddressInput)

        eventNameInput.setText(intent.getStringExtra("eventName"))
        eventAddressInput.setText(intent.getStringExtra("eventAddress"))

        val spinner: Spinner = findViewById(R.id.categorySpinner)
        val categories = CategoryDao(baseContext).getAll().map { it.name }
        val categorySpinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = categorySpinnerAdapter
        spinner.onItemSelectedListener = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.event_menu, menu)
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