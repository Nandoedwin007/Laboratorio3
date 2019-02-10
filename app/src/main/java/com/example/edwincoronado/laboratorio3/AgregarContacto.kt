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
import android.widget.TextView
import android.widget.Toast
import com.example.edwincoronado.laboratorio3.Logic.DatabaseHandler
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import com.example.edwincoronado.laboratorio3.Logic.Users
import kotlinx.android.synthetic.main.activity_agregar_contacto.*
import java.util.regex.Pattern

class AgregarContacto : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)

        //init db
        dbHandler = DatabaseHandler(this)

        val context:MyApplication = applicationContext as MyApplication


        val btnRegresarM = findViewById<Button>(R.id.btnRegresar)

        val btnAgregarM = findViewById<Button>(R.id.btnAgregarContacto)
//
        val tilNombreM = findViewById<TextInputEditText>(R.id.tilNombre)
        val tilTelefonoM = findViewById<TextInputEditText>(R.id.tilTelefono)
        val tilEmailM = findViewById<TextInputEditText>(R.id.tilEmail)

        //Variables donde se guardarán los nombres que se ingresaron een los TextInputLayout
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
    //Funciones utilizadas para validar los datos ingresados
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

        //Variables booleanas que utilizan las funciones de verificación de los datos ingresados
        val a:Boolean = validarNombre(nombre)
        val b:Boolean = validarTelefono(telefono)
        val c:Boolean = validarCorreo(correo)

        val tilNombreM = findViewById<TextView>(R.id.tilNombre)
        val tilTelefonoM = findViewById<TextInputEditText>(R.id.tilTelefono)
        val tilEmailM = findViewById<TextInputEditText>(R.id.tilEmail)

        //Si los datos ingresados son válidos entonces se procede a agregarlos a la lista de contactos
        if(a&&b&&c){

            val user:Users = Users()
            var success:Boolean = false

            user.nombreCompleto = nombre
            user.telefono = telefono
            user.correo = correo

            success = dbHandler!!.addUser(user)

//            val contactoNuevo = MyContacts(nombre,telefono,correo)
//            context.MisContactos.add(contactoNuevo)
             Toast.makeText(getBaseContext(), "Se ha añadido a " + nombre + "  a los contactos", Toast.LENGTH_LONG).show()
//            //Luego de anadir en texto se elimina el texto ingresado
            tilNombreM.setText("")
            tilTelefonoM.setText("")
            tilEmailM.setText("")

        }
        else {
            Toast.makeText(getBaseContext(), "Los datos del contacto NO son válidos, revíselos por favor", Toast.LENGTH_LONG).show()
        }

    }



}
