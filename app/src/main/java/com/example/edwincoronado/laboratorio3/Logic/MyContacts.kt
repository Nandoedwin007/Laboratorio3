package com.example.edwincoronado.laboratorio3.Logic

class MyContacts:Contacts {

    override var nombre:String = ""
    override var telefono:String = ""
    override var email:String = ""


    fun add(nombreC: String,telefonoC: String,emailC: String) {
        nombre = nombreC
        telefono = telefonoC
        email = emailC
    } //Agregar elemento
}