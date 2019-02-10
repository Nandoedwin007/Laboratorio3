package com.example.edwincoronado.laboratorio3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.edwincoronado.laboratorio3.Logic.ContactosAdapter
import com.example.edwincoronado.laboratorio3.Logic.DatabaseHandler
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var dbHandler: DatabaseHandler? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init db
        dbHandler = DatabaseHandler(this@MainActivity)

        //val context:MyApplication = applicationContext as MyApplication
        //Estas líneas se utilizaban para no comenzar la lista de contactos vacía pero no afecta el no poseerla
        //val contactoPrueba = MyContacts("Edwin Coronado","12345678","cor14148@uvg.edu.gt")
        //context.MisContactos.add(contactoPrueba)


        //val mostrarContactos = context.MisContactos

        var users = dbHandler!!.getAllUsers()


        //Se define el adaptador a utilizar, nótese que es un CustomAdapter ya que logra mostrar el nombre
        // y número de teléfono en diferentes textviews
        val adaptador = ContactosAdapter(this@MainActivity, users)
        lvContactos.adapter = adaptador

        adaptador.notifyDataSetChanged()


        //Función que agrega los elemntos al ArrayList global (el que se generó el la clase MyApplication)
        // Ya que este posee la orden actual
        lvContactos.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view:View, position:Int,
                                     id:Long) {

                val item:Int = position

                Log.d("Posicion Main",item.toString())

                //Se crea el intent para iniciar la otra actividad
                val intent = Intent(this@MainActivity, VerContacto::class.java)
                //Sin embargo, es necesario enviar la posición del contacto presionado para que pueda mostrarse en la siguiente Activity
                val parametro = Bundle()
                parametro.putInt("Posicion",item)
                intent.putExtras(parametro)
                startActivity(intent)
                adaptador.notifyDataSetChanged()


            }
        })


        //Definimos un objeto tipo ListView usando el Id del XML
        //val listView:ListView = findViewById(R.id.ltPedido)
        //listView.setAdapter(adaptador)

        //Funcion que hace uso del OnItemLongClickListener para poder remover elementos de ArrayList
        lvContactos.setOnItemLongClickListener(object:AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(arg0:AdapterView<*>, view: View,
                                         pos:Int, arg3:Long):Boolean {

                var item:Int = pos

                //item = item +1
                var user = dbHandler!!.delUser(item)
                Toast.makeText(getBaseContext(), "Se ha REMOVIDO el contacto con el ID "+ item, Toast.LENGTH_LONG).show()
                val adaptador = ContactosAdapter(this@MainActivity, users)
                lvContactos.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
        })


        val button = findViewById<Button>(R.id.btnAgregar)
        //Funcion que abre la activity de MostrarMenu
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                val intent = Intent(this@MainActivity, AgregarContacto::class.java)
                startActivity(intent)
                adaptador.notifyDataSetChanged()
            }
        })


    }

    override fun onResume() {
        super.onResume()

        //val context:MyApplication = applicationContext as MyApplication
        //val contactoPrueba = MyContacts("Edwin","12345678","edwin@gmail.com")
        //context.MisContactos.add(contactoPrueba)

        //val mostrarContactos = context.MisContactos

        dbHandler!!.close()
        //init db
        dbHandler = DatabaseHandler(this@MainActivity)

        var users = dbHandler!!.getAllUsers()
        //val adaptador = ContactosAdapter(this, users)





        //Se define el adaptador a utilizar, nótese que es un CustomAdapter ya que logra mostrar el nombre
        // y número de teléfono en diferentes textviews
        val adaptador = ContactosAdapter(this@MainActivity, users)
        lvContactos.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}
