package com.example.actividad_24_09_2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class consulta : AppCompatActivity() {

    //private
    private lateinit var info: TextView
    private var beca: Beca = Beca()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        //Asociar con componente  graficos
        info = findViewById(R.id.txtInfo)

        //Instancia para recibir informacion
        val infoRecibida = intent.extras

        beca.folio = infoRecibida?.getInt("folio")!!
        beca.institucion = infoRecibida?.getString("institucion")!!
        beca.nombre = infoRecibida?.getString("nombre")!!
        beca.apellido = infoRecibida?.getString("apellido")!!
        beca.nivel = infoRecibida?.getString("nivel")!!

        //Colocar la informacion dentro del TextView
        info.text = "Informacion registrada: \n"+
                "Folio: ${beca.folio} \n" +
                "Institucion: ${beca.institucion} \n"+
                "Nombre: ${beca.nombre} \n"+
                "Apellido: ${beca.apellido} \n"+
                "${beca.nivel}"

    }//onCreate
}//Class