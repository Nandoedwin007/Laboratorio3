package com.example.edwincoronado.laboratorio3

import android.app.Application
import com.example.edwincoronado.laboratorio3.Logic.MyContacts
import java.util.*
import kotlin.collections.ArrayList

class MyApplication:Application(){

    var MisContactos = ArrayList<MyContacts>()

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

    }


    override fun onCreate() {
        super.onCreate()




    }


}