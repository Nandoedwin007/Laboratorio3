package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_enviar_correo.*
import java.time.chrono.MinguoChronology

class EnviarCorreo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_correo)



        val b:Bundle = intent.extras
        val destinatario = b.getString("Destinatario")

        //Log.d("Position ",""+posicionContacto)

        val context:MyApplication = applicationContext as MyApplication

        val MiNombre:String = "Edwin Coronado"
        val MiNumero:String = "11223344"

        val tvNombreDe = findViewById<TextView>(R.id.tvDe)
        val tvNombrePara = findViewById<TextView>(R.id.tvPara)
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)

        val mensaje:String = "Mi nombre es "+MiNombre+", y mi telefóno es "+MiNumero

        tvNombreDe.text = MiNombre
        tvNombrePara.text = destinatario
        tvMensaje.text = mensaje


        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        //Funcion que abre la activity de MostrarMenu
        btnEnviar.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.setData(Uri.parse("mailto:"))
                emailIntent.setType("text/plain")
                emailIntent.putExtra(Intent.EXTRA_EMAIL,destinatario)
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Saludos desde mi Applicación")
                emailIntent.putExtra(Intent.EXTRA_TEXT,"Mi nombre es "+MiNombre+", y mi telefóno es "+MiNumero)

                startActivity(Intent.createChooser(emailIntent, "Send email..."))
                finish()
                Log.i("Accion","Se ha enviado el correo")

                Snackbar.make(root_layout,"Enviado desde " +MiNombre+" hasta "+destinatario,Snackbar.LENGTH_SHORT).show()
            }
        })


    }




}
