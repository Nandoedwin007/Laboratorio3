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
        val contactoPrueba = MyContacts("Edwin","12345678","edwin@gmail.com")
        context.MisContactos.add(contactoPrueba)


        val mostrarContactos = context.MisContactos


        //Codigo para custom adapter NO FUNCIONA
        //--------------------
        //Definimos un objeto tipo ArrayAdapter
//        val adaptador = ContactosAdapter(this,mostrarContactos)
//
//
//        //Definimos un objeto tipo ListView usando el Id del XML
//        val listView: ListView = findViewById(R.id.lvContactos)
//
//        listView.setAdapter(adaptador)
        //----------------------


        //Codigo para Adapter normal pero NO muestra Nombre y Celular
        //val adaptador = ArrayAdapter(this,R.layout.simple_list_item1,mostrarContactos)



        //Definimos un objeto tipo ListView usando el Id del XML
        //val listView:ListView = findViewById(R.id.lvContactos)
        //listView.setAdapter(adaptador)

        val adaptador = ContactosAdapter(this, mostrarContactos)
        lvContactos.adapter = adaptador

        adaptador.notifyDataSetChanged()


        //Función que agrega los elemntos al ArrayList global (el que se generó el la clase MyApplication)
        // Ya que este posee la orden actual
        lvContactos.setOnItemClickListener(object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view:View, position:Int,
                                     id:Long) {
                //val item = (view as TextView).getText().toString()
                //Toast.makeText(getBaseContext(), "Se ha añaido " +item+ " a su orden", Toast.LENGTH_LONG).show()
                val item:Int = position
                //Log.d("Position ",""+position)

                val intent = Intent(this@MainActivity, VerContacto::class.java)
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

        val buttonRefrescar = findViewById<Button>(R.id.btnRefrescar)
        //Funcion que abre la activity de MostrarMenu
        buttonRefrescar.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                adaptador.notifyDataSetChanged()
            }
        })






    }

}
