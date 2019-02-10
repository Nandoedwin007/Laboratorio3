package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.edwincoronado.laboratorio3.Logic.DatabaseHandler
import com.example.edwincoronado.laboratorio3.Logic.Users
import kotlinx.android.synthetic.main.activity_editar_contacto.*
import java.util.regex.Pattern

class EditarContacto : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_contacto)

        //init db
        dbHandler = DatabaseHandler(this)

        var tilNombreContactoEdit = findViewById<TextInputEditText>(R.id.tilNombreEdit)
        var tilTelefonoContactoEdit = findViewById<TextInputEditText>(R.id.tilTelefonoEdit1)
        var tilCorreoContactoEdit = findViewById<TextInputEditText>(R.id.tilCorreoEdit1)

        //val tilNombreEdit = findViewById<TextView>(R.id.tilNombreEdit)
        //val tilTelefonoEdit = findViewById<TextView>(R.id.tilTelefonoEdit)
        //val tilCorreoEdit = findViewById<TextView>(R.id.tilCorreoEdit)


        val b:Bundle = intent.extras
        var posicionContactoEdit = b.getInt("Posicion")

        //Una vez con el dato de posición de utiliza el array de objetos de clase contacto para obtener los datos requeridos
        //val context:MyApplication = applicationContext as MyApplication


        var user = dbHandler!!.getContacto(posicionContactoEdit)

        var nombreActualE:String = user.nombreCompleto
        var telefonoActualE:String = user.telefono
        var emailActualE:String = user.correo



        //tilNombreContactoEdit.hint = nombreActualE
        tilNombreContactoEdit.setText(nombreActualE)
         //tilTelefonoContactoEdit.hint = telefonoActualE
        tilTelefonoContactoEdit.setText(telefonoActualE)
        //tilCorreoContactoEdit.hint = emailActualE
        tilCorreoContactoEdit.setText(emailActualE)

        //Variables donde se guardarán los nombres que se ingresaron een los TextInputLayout
        var nombreNuevoEd:String
        var telefonoNuevoEd:String
        var emailNuevoEd:String

        val btnguardar = findViewById<Button>(R.id.btnGuardar)

        //Funcion que abre la activity de MostrarMenu
        btnguardar.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.

                nombreNuevoEd = tilNombreContactoEdit.text.toString()
                telefonoNuevoEd = tilTelefonoContactoEdit.text.toString()
                emailNuevoEd = tilCorreoContactoEdit.text.toString()
                validarDatos(nombreNuevoEd,telefonoNuevoEd,emailNuevoEd,posicion = posicionContactoEdit)



            }
        })







        val btnRegresarEditar = findViewById<Button>(R.id.btnRegresarE)

        btnRegresarE.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                finish()
            }
        })
    }

    //Funciones utilizadas para validar los datos ingresados
    fun validarNombre(nombre:String):Boolean{
        val patron: Pattern = Pattern.compile("^[a-zA-Z ]+$")
        return patron.matcher(nombre).matches() || nombre.length>30
    }


    fun validarTelefono(telefono:String):Boolean{
        return Patterns.PHONE.matcher(telefono).matches()
    }

    fun validarCorreo(correo:String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(correo).matches()
    }

    fun validarDatos(nombre: String,telefono: String,correo: String, posicion:Int){
        val context:MyApplication = applicationContext as MyApplication

        //Variables booleanas que utilizan las funciones de verificación de los datos ingresados
        val a:Boolean = validarNombre(nombre)
        val b:Boolean = validarTelefono(telefono)
        val c:Boolean = validarCorreo(correo)

        var tilNombreContactoEdit = findViewById<TextInputEditText>(R.id.tilNombreEdit)
        var tilTelefonoContactoEdit = findViewById<TextInputEditText>(R.id.tilTelefonoEdit1)
        var tilCorreoContactoEdit = findViewById<TextInputEditText>(R.id.tilCorreoEdit1)

        //Si los datos ingresados son válidos entonces se procede a agregarlos a la lista de contactos
        if(a&&b&&c){

            val user: Users = Users()
            var success:Boolean = false

            user.nombreCompleto = nombre
            user.telefono = telefono
            user.correo = correo

            success = dbHandler!!.updateUser(posicion,user)

//            val contactoNuevo = MyContacts(nombre,telefono,correo)
//            context.MisContactos.add(contactoNuevo)
            Toast.makeText(getBaseContext(), "Se ha modificado a " + nombre + "  en los contactos", Toast.LENGTH_LONG).show()
//            //Luego de anadir en texto se elimina el texto ingresado
            tilNombreContactoEdit.setText("")
            tilTelefonoContactoEdit.setText("")
            tilCorreoContactoEdit.setText("")

        }
        else {
            Toast.makeText(getBaseContext(), "Los datos del contacto NO son válidos, revíselos por favor", Toast.LENGTH_LONG).show()
        }

    }
}
