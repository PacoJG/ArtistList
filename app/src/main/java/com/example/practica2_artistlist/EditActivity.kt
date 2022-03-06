package com.example.practica2_artistlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica2_artistlist.databinding.ActivityEditBinding
import com.example.practica2_artistlist.db.DBArtist
import com.example.practica2_artistlist.model.Artist

class EditActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityEditBinding
    private lateinit var dbArtist: DBArtist
    var artist: Artist? = null
    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            val bundle = intent.extras
            if (bundle != null){
                id = bundle.getInt("id", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("id") as Int
        }

        dbArtist = DBArtist(this)
        artist = dbArtist.getArtist(id)
        if(artist != null){
            with(binding){
                tieName.setText(artist?.name)
                actvStage.setText(artist?.stage)
                tieGenre.setText(artist?.genre)
                tieSchedule.setText(artist?.schedule)
            }
        }

        val items = resources.getStringArray(R.array.stage_list)
        val adapter = ArrayAdapter(this@EditActivity, R.layout.item_form, items)

        with(binding.actvStage){
            setAdapter(adapter)
            onItemClickListener = this@EditActivity
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position)
        {
            0 ->{
                Toast.makeText(this@EditActivity,getString(R.string.seleccion_stage1),Toast.LENGTH_LONG).show()
            }
            1 ->{
                Toast.makeText(this@EditActivity,getString(R.string.seleccion_stage2),Toast.LENGTH_LONG).show()
            }
            2 ->{
                Toast.makeText(this@EditActivity,getString(R.string.seleccion_stage3),Toast.LENGTH_LONG).show()
            }
            3 ->{
                Toast.makeText(this@EditActivity,getString(R.string.seleccion_stage4),Toast.LENGTH_LONG).show()
            }
            4 ->{
                Toast.makeText(this@EditActivity,getString(R.string.seleccion_stage5),Toast.LENGTH_LONG).show()
            }
        }
    }

    fun click(view: View) {
        with(binding){
            if(!tieName.text.toString().isEmpty() && !actvStage.text.toString().isEmpty() && !tieGenre.text.toString().isEmpty() && !tieSchedule.text.toString().isEmpty()){
                if(dbArtist.updateArtist(id, tieName.text.toString(), actvStage.text.toString(), tieGenre.text.toString(),tieSchedule.text.toString())){
                    Toast.makeText(this@EditActivity, "Registro actualizado exitosamente", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@EditActivity, MainActivity::class.java)
                    intent.putExtra("id",id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@EditActivity, "Error al actualizar el registro", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@EditActivity, "Todos los campos debene estar llenos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@EditActivity, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        finish()
    }
}