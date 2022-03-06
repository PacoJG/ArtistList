package com.example.practica2_artistlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.practica2_artistlist.R
import com.example.practica2_artistlist.databinding.ListItemBinding
import com.example.practica2_artistlist.model.Artist

class ArtistsAdapter (contexto: Context, listArtists: ArrayList<Artist>) : BaseAdapter() {

    private val listArtists = listArtists
    private val layoutInflater = LayoutInflater.from(contexto)

    override fun getCount(): Int {
        return listArtists.size
    }

    override fun getItem(p0: Int): Any {
        return listArtists[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listArtists[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ListItemBinding.inflate(layoutInflater)
        with(binding){
            tvName.text = listArtists[p0].name
            tvGenre.text = listArtists[p0].genre
            tvSchedule.text = listArtists[p0].schedule
            tvStage.text = listArtists[p0].stage
            when(listArtists[p0].stage){
                "wasteLAND" ->{
                    ivStage.setImageResource(R.drawable.wasteland)
                }
                "DOS EQUIS STAGE" ->{
                    ivStage.setImageResource(R.drawable.dosequisstage)
                }
                "kineticFIELD" ->{
                    ivStage.setImageResource(R.drawable.kineticfield)
                }
                "circuitGROUNDS" ->{
                    ivStage.setImageResource(R.drawable.circuitgrounds)
                }
                "neonGARDEN" ->{
                    ivStage.setImageResource(R.drawable.neongarden)
                }
            }
        }
        return binding.root
    }
}