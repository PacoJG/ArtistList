package com.example.practica2_artistlist.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.practica2_artistlist.model.Artist
import java.lang.Exception

class DBArtist(context: Context?):DBHelper(context){

    val context = context

    fun insertArtist(name: String, genre: String, stage: String, schedule: String): Long{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0

        try {
            val values = ContentValues()
            values.put("name",name)
            values.put("stage",stage)
            values.put("genre",genre)
            values.put("schedule",schedule)
            id = db.insert(TABLE_ARTIST,null,values)
        }catch (e: Exception){

        }finally {
            db.close()
        }
        return id
    }

    fun getArtists(): ArrayList<Artist>{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var listArtists = ArrayList<Artist>()
        var artistTmp: Artist? = null
        var cursorArtists: Cursor? = null

        cursorArtists = db.rawQuery("SELECT * FROM $TABLE_ARTIST",null)

        if (cursorArtists.moveToFirst()){
            do{
                artistTmp = Artist(cursorArtists.getInt(0),cursorArtists.getString(1),cursorArtists.getString(2),cursorArtists.getString(3),cursorArtists.getString(4))
                listArtists.add(artistTmp)
            }while (cursorArtists.moveToNext())
        }
        cursorArtists.close()
        return listArtists
    }

    fun getArtist(id: Int): Artist?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var artist: Artist? = null
        var cursorArtists: Cursor? = null

        cursorArtists = db.rawQuery("SELECT * FROM $TABLE_ARTIST WHERE id = $id LIMIT 1",null)

        if (cursorArtists.moveToFirst()){
            artist = Artist(cursorArtists.getInt(0),cursorArtists.getString(1),cursorArtists.getString(2),cursorArtists.getString(3),cursorArtists.getString(4))
        }

        cursorArtists.close()
        return artist
    }

    fun updateArtist(id: Int, name: String, genre: String, stage: String, schedule: String): Boolean{
        var banderaCorrecto = false
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        try {
            db.execSQL("UPDATE $TABLE_ARTIST SET name = '$name', stage = '$stage', genre = '$genre', schedule = '$schedule' WHERE id = $id")
            banderaCorrecto = true
        }catch (e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }


    fun deleteArtist(id: Int): Boolean{
        var banderaCorrecto = true
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("DELETE FROM $TABLE_ARTIST WHERE id = $id")
            banderaCorrecto = true
        }catch (e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }
}
