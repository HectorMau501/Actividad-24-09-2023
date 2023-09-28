package com.example.actividad_24_09_2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.actividad_24_09_2023.R.id.editInstitucion

class registro : AppCompatActivity() {

    //Intancias
    private lateinit var folio: EditText
    private lateinit var institucion: EditText
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var nivel: Spinner
    private var beca: Beca = Beca()
    private lateinit var nivelSel: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Asociar con componentes graficos
        folio = findViewById(R.id.editFolio)
        institucion = findViewById(editInstitucion)
        nombre = findViewById(R.id.editNombre)
        apellido = findViewById(R.id.editApellido)
        nivel = findViewById(R.id.spnNivel)
        //Definir valores de nivel
        val opciones = resources.getStringArray(R.array.niveles)
        //Vincular las opciones con el componente Spinner
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,opciones)
        nivel.adapter = adaptador
        //Opcion predeterminada
        nivelSel = opciones[0]
        //Escucha para determinar la opcion Seleccionada del Spinner
        nivel.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                nivelSel = opciones[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }//AdapterView
    }//OnCreate

    fun onClick(view: View?){
        when(view?.id){
            R.id.btnRegistrar -> agregar()
            R.id.btnLimpiar2 -> limpiar2()
        }
    }

    private fun limpiar2() {
        folio.text = null
        institucion.text = null
        nombre.text = null
        apellido.text = null
        folio.requestFocus()
    }

    private fun agregar() {
        //validar que exista informacion en cajas de texto
        if(folio.text.isNotEmpty() && folio.text.isNotBlank() && institucion.text.isNotEmpty()
            && institucion.text.isNotBlank() && nombre.text.isNotEmpty() && nombre.text.isNotBlank()
            && apellido.text.isNotEmpty() && apellido.text.isNotBlank()){

            beca.folio = folio.text.toString().toInt()
            beca.institucion = institucion.text.toString()
            beca.nombre = nombre.text.toString()
            beca.apellido = apellido.text.toString()
            beca.nivel = nivelSel
            //Mensaje informativo
            Toast.makeText(this,"Informacion Registrada",Toast.LENGTH_LONG).show()
            //Registrar al menu principal
            val intent = Intent(this,menu::class.java)
            intent.putExtra("folio",beca.folio)
            intent.putExtra("institucion",beca.institucion)
            intent.putExtra("nombre",beca.nombre)
            intent.putExtra("apellido",beca.apellido)
            intent.putExtra("nivel",beca.nivel)

            // Iniciar la actividad menu con el Intent
            startActivity(intent)
        }else{
            Toast.makeText(this,"Capturar informacion",Toast.LENGTH_LONG).show()
        }
    }//agregar


}//class