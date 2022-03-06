package com.example.practica2_artistlist.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_ARTIST (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, stage TEXT NOT NULL, genre TEXT NOT NULL,  schedule TEXT NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        p0?.execSQL("DROP TABLE $TABLE_ARTIST")
        onCreate(p0)
    }

    //Asi se manejan los statics en kotlin
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "artist_list.db"
        public const val TABLE_ARTIST = "artist"
    }
}