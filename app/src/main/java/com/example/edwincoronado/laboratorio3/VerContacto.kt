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
import com.example.edwincoronado.laboratorio3.Logic.DatabaseHandler
import java.util.jar.Manifest

class VerContacto : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_contacto)

        //init db
        dbHandler = DatabaseHandler(this)

        val MY_PERMISSIONS_REQUEST_CALL_PHONE:Int = 1;

        //Como se mencionó en la MainActivity, el dato de la posición del contacto se envió y se utilizan las siguientes dos líneas
        // para recuperar este parámetro y así obtener los datos del nombre, telefono y correo del contacto
        val b:Bundle = intent.extras
        var posicionContacto = b.getInt("Posicion")

        //Una vez con el dato de posición de utiliza el array de objetos de clase contacto para obtener los datos requeridos
        //val context:MyApplication = applicationContext as MyApplication

        posicionContacto = posicionContacto +1
        var user = dbHandler!!.getContacto(posicionContacto)

        val nombreActual:String = user.nombreCompleto
        val telefonoActual:String = user.telefono
        val emailActual:String = user.correo

//        var nombreActual:String = "11"
//        var telefonoActual:String = "22"
//        var emailActual:String = "33"
//
//        nombreActual = user.nombreCompleto
//        telefonoActual = user.telefono
//        //emailActual = user.correo

        //Se definen los TextViews para poder utilizarlos en el futuro
        val textViewNombre= findViewById<TextView>(R.id.tvNombre)
        val textViewTelefono= findViewById<TextView>(R.id.tvTelefono)
        val textViewEmail= findViewById<TextView>(R.id.tvEmail)

        textViewNombre.text = nombreActual
        textViewTelefono.text = telefonoActual
        textViewEmail.text = emailActual

        //Esta linea permite hacer link con la aplicacion de telefono, es util pero no la deseada
        //Linkify.addLinks(textViewTelefono, Linkify.ALL)

        //Función que dectecta si se precionó el TextView que posee el número de teléfono
        textViewTelefono.setOnClickListener{
            val phoneIntent = Intent(Intent.ACTION_CALL)
            phoneIntent.setData(Uri.parse("tel:"+telefonoActual))


            if ((ActivityCompat.checkSelfPermission(this@VerContacto,
                    android.Manifest.permission.CALL_PHONE) !== PackageManager.PERMISSION_GRANTED))
            {
                //En caso que no posea el permiso necesario se sale de la función setOnClickListener
                return@setOnClickListener
            }
            //Si posee los permismos necesarios inicia el Intent para realizar llamadas
            startActivity(phoneIntent)

        }

        //Función que detecta si se presionó el TextView del correo electrónico
        //Nuevamente se hace un Intent para iniciar otra actividad pero en este caso también
        //se envía el dato del correo al cual se enviará el mensaje por medio de la función
        //putString()
        textViewEmail.setOnClickListener{
            val intent = Intent(this@VerContacto, EnviarCorreo::class.java)
            val parametro = Bundle()
            parametro.putString("Destinatario",emailActual)
            intent.putExtras(parametro)
            startActivity(intent)

        }


        val btnEditarCon = findViewById<Button>(R.id.btnEditar)

        btnEditarCon.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@VerContacto, EditarContacto::class.java)
                val parametro = Bundle()
                parametro.putInt("Posicion",posicionContacto)
                intent.putExtras(parametro)
                startActivity(intent)
            }
        })

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
