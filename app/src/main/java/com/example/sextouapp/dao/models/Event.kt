package com.example.sextouapp.dao.models

class Event {
    companion object {
        const val TABLE  = "events"
        const val ID  = "id"
        const val NAME  = "name"
        const val ADDRESS  = "address"
        const val CATEGORY_ID  = "category_id"

        val SQL_CREATE = "CREATE TABLE $TABLE ( " +
                        "$ID INTEGER PRIMARY KEY AUTOINCREMENT " +
                        "$NAME TEXT " +
                        "$ADDRESS TEXT " +
                        "FOREIGN KEY($CATEGORY_ID) REFERENCES ${Category.TABLE} (${Category.ID}));";
    }
}

// id, nome, endereço, categoria_id