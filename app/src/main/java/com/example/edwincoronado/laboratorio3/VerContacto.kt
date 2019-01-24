package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.jar.Manifest

class VerContacto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_contacto)
        val MY_PERMISSIONS_REQUEST_CALL_PHONE:Int = 1;

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

        //Esta linea permite hacer link con la aplicacion de telefono, es util pero no la deseada
        //Linkify.addLinks(textViewTelefono, Linkify.ALL)

        textViewTelefono.setOnClickListener{
            val phoneIntent = Intent(Intent.ACTION_CALL)
            phoneIntent.setData(Uri.parse("tel:"+telefonoActual))


            if ((ActivityCompat.checkSelfPermission(this@VerContacto,
                    android.Manifest.permission.CALL_PHONE) !== PackageManager.PERMISSION_GRANTED))
            {
                return@setOnClickListener
            }
            startActivity(phoneIntent)

        }

        textViewEmail.setOnClickListener{


            //val item:Int = position

            val intent = Intent(this@VerContacto, EnviarCorreo::class.java)
            val parametro = Bundle()
            parametro.putString("Destinatario",emailActual)
            intent.putExtras(parametro)
            startActivity(intent)

        }




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
