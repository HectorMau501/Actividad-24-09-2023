package com.example.actividad_24_09_2023

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    //Instancia Intent para lanzar una Activity
    private lateinit var intent: Intent
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beca = Beca()
        //Intancia para generar un hilo para lanzar Activity de Ingreso o Menu
        Timer().schedule(object : TimerTask(){
            override fun run() {
                intent  = if(nuevoUsuario()){
                    Intent(applicationContext, menu::class.java)
                    intent.putExtra("folio", beca.folio)
                    intent.putExtra("institucion",beca.institucion)
                    intent.putExtra("nombre",beca.nombre)
                    intent.putExtra("apellido",beca.apellido)
                    intent.putExtra("nivel",beca.nivel)
                } else{
                    Intent(applicationContext, ingreso::class.java)
                }
                startActivity(intent)
            }
        }, 2000) //Timer
    }//onCreate

    fun nuevoUsuario():Boolean{
        //Acceso a las preferencias
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)
        return preferences.getBoolean("guardado",false)
    }
}