package com.example.actividad_24_09_2023

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class menu : AppCompatActivity() {

    //Intancias
    private lateinit var beca: Beca

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Definir barra del menu desplegable (overflow)
        val toolbar:  Toolbar = findViewById(R.id.barra)
        setSupportActionBar(toolbar)
        //Inicializar instancia
        beca = Beca()
        //Instancia para recibir informacion
        var infoRecibida = intent.extras
        if(infoRecibida != null){
            beca.folio = infoRecibida?.getInt("folio")!!
            beca.institucion = infoRecibida?.getString("institucion")!!
            beca.nombre = infoRecibida?.getString("nombre")!!
            beca.apellido = infoRecibida?.getString("apellido")!!
            beca.nivel = infoRecibida?.getString("nivel")!!
        }//if
    }//onCreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)

        //return super.onCreateOptionsMenu(menu)
        return true
    }//onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent?
        when(item.itemId){
            R.id.itmFormulario -> {
                intent = Intent(applicationContext, registro::class.java)
                startActivity(intent)
            }
            R.id.itmConsultar -> {
                intent = Intent(applicationContext, consulta::class.java)
                intent.putExtra("folio",beca.folio)
                intent.putExtra("institucion",beca.institucion)
                intent.putExtra("nombre",beca.nombre)
                intent.putExtra("apellido",beca.apellido)
                intent.putExtra("nivel",beca.nivel)
                startActivity(intent)
            }
            R.id.itmCerrar -> { cerrarSesion() }
        }
        return super.onOptionsItemSelected(item)
    }//onOptionsItemSelected

    private fun cerrarSesion() {
        //Instancia donde se almacenara la informacion
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)
        //Editor de preferencias, para agregar, asociado con preferencias
        val editor: SharedPreferences.Editor = preferences.edit()
        //borrar informacion almacenada
        editor.clear()
        editor.apply()
        //Regresar a InicioActivity
        val intent = Intent(applicationContext, ingreso::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP; Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }//cerraSesion
}