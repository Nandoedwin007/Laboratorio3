package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.edwincoronado.laboratorio3.Logic.ContactosAdapter
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context:MyApplication = applicationContext as MyApplication
        //Estas líneas se utilizaban para no comenzar la lista de contactos vacía pero no afecta el no poseerla
        //val contactoPrueba = MyContacts("Edwin Coronado","12345678","cor14148@uvg.edu.gt")
        //context.MisContactos.add(contactoPrueba)


        val mostrarContactos = context.MisContactos


        //Se define el adaptador a utilizar, nótese que es un CustomAdapter ya que logra mostrar el nombre
        // y número de teléfono en diferentes textviews
        val adaptador = ContactosAdapter(this, mostrarContactos)
        lvContactos.adapter = adaptador

        adaptador.notifyDataSetChanged()


        //Función que agrega los elemntos al ArrayList global (el que se generó el la clase MyApplication)
        // Ya que este posee la orden actual
        lvContactos.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view:View, position:Int,
                                     id:Long) {

                val item:Int = position

                //Se crea el intent para iniciar la otra actividad
                val intent = Intent(this@MainActivity, VerContacto::class.java)
                //Sin embargo, es necesario enviar la posición del contacto presionado para que pueda mostrarse en la siguiente Activity
                val parametro = Bundle()
                parametro.putInt("Posicion",item)
                intent.putExtras(parametro)
                startActivity(intent)


            }
        })

        val button = findViewById<Button>(R.id.btnAgregar)
        //Funcion que abre la activity de MostrarMenu
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@MainActivity, AgregarContacto::class.java)
                startActivity(intent)
            }
        })


    }

    override fun onResume() {
        super.onResume()

        val context:MyApplication = applicationContext as MyApplication
        //val contactoPrueba = MyContacts("Edwin","12345678","edwin@gmail.com")
        //context.MisContactos.add(contactoPrueba)

        val mostrarContactos = context.MisContactos
        val adaptador = ContactosAdapter(this, mostrarContactos)
        lvContactos.adapter = adaptador

        adaptador.notifyDataSetChanged()
    }

}
