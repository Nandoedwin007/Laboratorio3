package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat.startActivity
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import kotlinx.android.synthetic.main.activity_agregar_contacto.*
import java.util.regex.Pattern

class AgregarContacto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)

        val context:MyApplication = applicationContext as MyApplication


        val btnRegresarM = findViewById<Button>(R.id.btnRegresar)

        val btnAgregarM = findViewById<Button>(R.id.btnAgregarContacto)
//
        val tilNombreM = findViewById<TextInputEditText>(R.id.tilNombre)
        val tilTelefonoM = findViewById<TextInputEditText>(R.id.tilTelefono)
        val tilEmailM = findViewById<TextInputEditText>(R.id.tilEmail)


        var nombreNuevo:String
        var telefonoNuevo:String
        var emailNuevo:String

        //Funcion que abre la activity de MostrarMenu
        btnRegresarM.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                finish()
            }
        })


        //Funcion que abre la activity de MostrarMenu
        btnAgregarM.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.

                nombreNuevo = tilNombreM.text.toString()
                telefonoNuevo = tilTelefonoM.text.toString()
                emailNuevo = tilEmailM.text.toString()
                validarDatos(nombreNuevo,telefonoNuevo,emailNuevo)



            }
        })



    }

    fun validarNombre(nombre:String):Boolean{
        val patron:Pattern = Pattern.compile("^[a-zA-Z ]+$")
        return patron.matcher(nombre).matches() || nombre.length>30
    }

    fun validarTelefono(telefono:String):Boolean{
        return Patterns.PHONE.matcher(telefono).matches()
    }

    fun validarCorreo(correo:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    fun validarDatos(nombre: String,telefono: String,correo: String){
        val context:MyApplication = applicationContext as MyApplication
        val a:Boolean = validarNombre(nombre)
        val b:Boolean = validarTelefono(telefono)
        val c:Boolean = validarCorreo(correo)

        if(a&&b&&c){


            val contactoNuevo = MyContacts(nombre,telefono,correo)
            context.MisContactos.add(contactoNuevo)
            Toast.makeText(getBaseContext(), "Se ha añadido a " + nombre + "  a los contactos", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(getBaseContext(), "Los datos del contacto NO son válidos, revíselos por favor", Toast.LENGTH_LONG).show()
        }

    }



}
