package com.example.practica2_artistlist

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.practica2_artistlist.databinding.ActivityDetailsBinding
import com.example.practica2_artistlist.db.DBArtist
import com.example.practica2_artistlist.model.Artist

class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dbArtists: DBArtist
    var artist: Artist? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            val bundle = intent.extras
            if (bundle != null){
                id = bundle.getInt("id", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("id") as Int
        }

        dbArtists = DBArtist(this)
        artist = dbArtists.getArtist(id)
        if(artist != null){
            with(binding){
                tieName.setText(artist?.name)
                actvStage.setText(artist?.stage)
                tieGenre.setText(artist?.genre)
                tieSchedule.setText(artist?.schedule)
                //bloquear el escribir en los botones
                tieName.inputType = InputType.TYPE_NULL
                actvStage.inputType = InputType.TYPE_NULL
                tieGenre.inputType = InputType.TYPE_NULL
                tieSchedule.inputType = InputType.TYPE_NULL
            }
        }

    }

    fun click(view: View) {
        when(view.id){
            R.id.btnEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete ->{
                AlertDialog.Builder(this)
                    .setTitle("Confirmacion")
                    .setMessage("¿Realmente deseas eliminar al artista ${artist?.name}?")
                    .setPositiveButton("Si", DialogInterface.OnClickListener { dialog, which ->
                        if (dbArtists.deleteArtist(id)){
                            Toast.makeText(this, "Registro eliminado exitosamente", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        }
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                        //Si se desea realizar una acción cuando el usuario selecciona NO
                    })
                    .show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }
}