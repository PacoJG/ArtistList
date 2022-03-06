package com.example.practica2_artistlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practica2_artistlist.databinding.ActivityInsertBinding
import com.example.practica2_artistlist.db.DBArtist

class InsertActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = resources.getStringArray(R.array.stage_list)
        val adapter = ArrayAdapter(this@InsertActivity, R.layout.item_form, items)

        with(binding.actvStage){
            setAdapter(adapter)
            onItemClickListener = this@InsertActivity
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(position)
        {
            0 ->{
                Toast.makeText(this@InsertActivity,getString(R.string.seleccion_stage1),Toast.LENGTH_LONG).show()
            }
            1 ->{
                Toast.makeText(this@InsertActivity,getString(R.string.seleccion_stage2),Toast.LENGTH_LONG).show()
            }
            2 ->{
                Toast.makeText(this@InsertActivity,getString(R.string.seleccion_stage3),Toast.LENGTH_LONG).show()
            }
            3 ->{
                Toast.makeText(this@InsertActivity,getString(R.string.seleccion_stage4),Toast.LENGTH_LONG).show()
            }
            4 ->{
                Toast.makeText(this@InsertActivity,getString(R.string.seleccion_stage5),Toast.LENGTH_LONG).show()
            }
        }
    }

    fun click(view: View) {
        val dbArtists = DBArtist(this)

        with(binding){
            if (!tieName.text.toString().isEmpty() && !actvStage.text.toString().isEmpty() && !tieGenre.text.toString().isEmpty() && !tieSchedule.text.toString().isEmpty()){
                val id = dbArtists.insertArtist(tieName.text.toString(),actvStage.text.toString(),tieGenre.text.toString(),tieSchedule.text.toString())
                if(id > 0){//se añadio nuevo artista correctamente
                    Toast.makeText(this@InsertActivity, "Registro guardado exitosamente", Toast.LENGTH_LONG).show()

                    //Reiniciamos las cajas de texto
                    tieName.setText("")
                    actvStage.setText("")
                    tieGenre.setText("")
                    tieSchedule.setText("")
                    tieName.requestFocus()
                }else{
                    Toast.makeText(this@InsertActivity, "Error al guardar el registro", Toast.LENGTH_LONG).show()
                }
            }else{
                if (tieName.text.toString() == ""){
                    tieName.error = getString(R.string.error_campo)
                }
                if (actvStage.text.toString() == ""){
                    actvStage.error = getString(R.string.error_campo)
                }
                if (tieGenre.text.toString() == ""){
                    tieGenre.error = getString(R.string.error_campo)
                }
                if (tieSchedule.text.toString() == ""){
                    tieSchedule.error = getString(R.string.error_campo)
                }
                Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}