package com.example.edwincoronado.laboratorio3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.edwincoronado.laboratorio3.Logic.ContactosAdapter
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import android.widget.SimpleAdapter
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




    }
}