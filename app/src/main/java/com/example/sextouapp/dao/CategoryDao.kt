package com.example.sextouapp.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.sextouapp.Database
import com.example.sextouapp.dao.models.Category
import com.example.sextouapp.dao.models.Event

class CategoryDao(context: Context?) {
    private lateinit var sqlOpen: SQLiteDatabase
    private var database: Database

    init {
        Log.d("SextouApp", "CategoryDao init()")
        database = Database(context)
    }

    fun getAll(): ArrayList<Category> {
        sqlOpen = database.readableDatabase

        val cursor = sqlOpen.query(
            Category.TABLE, null, null, null, null, null, null
        );

        val events = ArrayList<Category>()
        while (cursor.moveToNext()) {
            val category = Category(
                cursor.getString(cursor.getColumnIndexOrThrow(Category.NAME))
            )

            events.add(category)
        }
        cursor.close()

        return events
    }
}