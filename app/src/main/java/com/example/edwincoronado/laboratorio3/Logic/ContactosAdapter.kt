package com.example.edwincoronado.laboratorio3.Logic
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.edwincoronado.laboratorio3.R

class ContactosAdapter(private val context: Context, private val dataSource: ArrayList<MyContacts>):BaseAdapter(){


    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.item_contacto, parent, false)


        // Get title element
        val nombreTextView = rowView.findViewById(R.id.nombre_contacto) as TextView

// Get subtitle element
        val telefnoTextView = rowView.findViewById(R.id.telefono_contacto) as TextView

        // 1
        val miContacto = getItem(position) as MyContacts

// 2
        nombreTextView.text = miContacto.nombre
        telefnoTextView.text = miContacto.telefono
        //detailTextView.text = recipe.label

// 3
        //Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return rowView
    }

}