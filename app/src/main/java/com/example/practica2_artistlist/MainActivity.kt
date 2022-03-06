package com.example.practica2_artistlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.practica2_artistlist.adapter.ArtistsAdapter
import com.example.practica2_artistlist.databinding.ActivityMainBinding
import com.example.practica2_artistlist.db.DBArtist
import com.example.practica2_artistlist.model.Artist


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listArtists: ArrayList<Artist>
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbArtist = DBArtist(this)
        listArtists = dbArtist.getArtists()

        if(listArtists.size == 0 ) binding.tvSinRegistros.visibility = View.VISIBLE
        else binding.tvSinRegistros.visibility = View.INVISIBLE

        val artistsAdapter = ArtistsAdapter(this,listArtists)

        binding.lvListArtists.adapter = artistsAdapter

        binding.lvListArtists.setOnItemClickListener { adapterView, view, i, l ->
            // l es el id e i es la posición
            val intent = Intent(this , DetailsActivity::class.java)
            intent.putExtra("id" , l.toInt())
            startActivity(intent)
            finish()
        }

    }

    
    fun click(view: View) {
        startActivity(Intent(this,InsertActivity::class.java))
        finish()
    }
}