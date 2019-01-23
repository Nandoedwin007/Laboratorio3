package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import kotlinx.android.synthetic.main.activity_agregar_contacto.*

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

                val contactoNuevo = MyContacts(nombreNuevo,telefonoNuevo,emailNuevo)
                context.MisContactos.add(contactoNuevo)
                Toast.makeText(getBaseContext(), "Se ha añadido a " + nombreNuevo + "  a los contactos", Toast.LENGTH_LONG).show()


            }
        })




    }


}
