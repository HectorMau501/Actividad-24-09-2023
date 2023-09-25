package com.example.actividad_24_09_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner

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
//        institucion = findViewById(R.id.editInstitucion)
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

    
}//class