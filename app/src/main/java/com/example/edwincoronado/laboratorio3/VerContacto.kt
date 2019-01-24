package com.example.edwincoronado.laboratorio3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class VerContacto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_contacto)

        val b:Bundle = intent.extras
        val posicionContacto = b.getInt("Posicion")

        //Log.d("Position ",""+posicionContacto)

        val context:MyApplication = applicationContext as MyApplication
        val nombreActual:String = context.MisContactos[posicionContacto].nombre
        val telefonoActual:String = context.MisContactos[posicionContacto].telefono
        val emailActual:String = context.MisContactos[posicionContacto].email

        val textViewNombre= findViewById<TextView>(R.id.tvNombre)
        val textViewTelefono= findViewById<TextView>(R.id.tvTelefono)
        val textViewEmail= findViewById<TextView>(R.id.tvEmail)

        textViewNombre.text = nombreActual
        textViewTelefono.text = telefonoActual
        textViewEmail.text = emailActual

        val btnRegresarM = findViewById<Button>(R.id.btnRegresar)
        //Funcion que abre la activity de MostrarMenu
        btnRegresarM.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                finish()
            }
        })
    }
}
