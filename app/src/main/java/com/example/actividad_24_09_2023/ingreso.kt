package com.example.actividad_24_09_2023

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class ingreso : AppCompatActivity() {
    
    //instancias
    private lateinit var correo: EditText
    private lateinit var contrasena: EditText
    private lateinit var guardar: Switch
    private lateinit var beca: Beca
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)
        
        //Asociar con componentes graficos
        correo = findViewById(R.id.editCorreo)
        contrasena = findViewById(R.id.editContrasena)
        guardar = findViewById(R.id.swtGuardar)
        //Inicializar objeto
        beca = Beca()
    }//onCreate
    
    fun onClick(view: View?){
        when(view?.id){
            R.id.btnIngresar -> ingresar()
            R.id.btnLimpiar -> limpiar()
        }
    }//onClick

    private fun ingresar() {
        //Validar que existan datos
        if(correo.text.isNotEmpty() && correo.text.isNotEmpty() &&
            contrasena.text.isNotBlank() && contrasena.text.isNotEmpty()){
            
            val usr = Usuario(correo.text.toString(), contrasena.text.toString(), true)
            if(guardar.isChecked){
                guardarPreferencias(usr)
            }//if
            val intent = Intent(applicationContext, menu::class.java)
            intent.putExtra("folio", beca.folio)
            intent.putExtra("institucion", beca.institucion)
            intent.putExtra("nombre", beca.nombre)
            intent.putExtra("apellido", beca.apellido)
            intent.putExtra("nivel", beca.nivel)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Capturar informacion",Toast.LENGTH_LONG).show()
        }
    }//ingresar

    private fun guardarPreferencias(user: Usuario) {
        //Intancias donde se almacenara la informacion
        val preferences: SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)
        //Editar de preferencias, para agregar, asociando con preferencias
        val editor: SharedPreferences.Editor = preferences.edit()
        //Agregar las preferencias
        editor.putString("email",user.correo)
        editor.putString("password", user.contrasena)
        editor.putBoolean("guardado",user.guardado)
        //Guardarlas
        editor.apply()
    }//guardarPreferencias

    private fun limpiar() {
        correo.text = null
        contrasena.text = null
        correo.requestFocus()
    }//limpiar
}//class