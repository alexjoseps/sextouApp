package com.example.sextouapp.dao.models

class Category(var id: Int, var name: String) {
    companion object {
        const val TABLE = "categories"
        const val ID = "id"
        const val NAME = "name"

        val SQL_CREATE =
            "CREATE TABLE $TABLE ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT NOT NULL UNIQUE);";
    }
}