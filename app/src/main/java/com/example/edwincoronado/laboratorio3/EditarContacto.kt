package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.edwincoronado.laboratorio3.Logic.DatabaseHandler
import kotlinx.android.synthetic.main.activity_editar_contacto.*

class EditarContacto : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contacto)

        //init db
        dbHandler = DatabaseHandler(this)

        var tilNombreContactoEdit = findViewById<TextInputEditText>(R.id.tilNombreEdit)
        var tilTelefonoContactoEdit = findViewById<TextInputEditText>(R.id.tilTelefonoEdit)
        var tilCorreoContactoEdit = findViewById<TextInputEditText>(R.id.tilCorreoEdit)

        //val tilNombreEdit = findViewById<TextView>(R.id.tilNombreEdit)
        //val tilTelefonoEdit = findViewById<TextView>(R.id.tilTelefonoEdit)
        //val tilCorreoEdit = findViewById<TextView>(R.id.tilCorreoEdit)


        val b:Bundle = intent.extras
        var posicionContactoEdit = b.getInt("Posicion")

        //Una vez con el dato de posición de utiliza el array de objetos de clase contacto para obtener los datos requeridos
        //val context:MyApplication = applicationContext as MyApplication


        var user = dbHandler!!.getContacto(posicionContactoEdit)

        val nombreActualE:String = user.nombreCompleto
        val telefonoActualE:String = user.telefono
        val emailActualE:String = user.correo


        //tilNombreContactoEdit.setText(nombreActualE)
        //tilTelefonoContactoEdit.setText(telefonoActualE)
        //tilCorreoContactoEdit.setText(emailActualE)

        val btnRegresarEditar = findViewById<Button>(R.id.btnRegresarE)

        btnRegresarE.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                finish()
            }
        })
    }
}
