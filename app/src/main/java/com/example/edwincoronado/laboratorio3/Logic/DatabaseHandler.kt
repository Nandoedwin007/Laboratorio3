package com.example.edwincoronado.laboratorio3.Logic

import com.example.edwincoronado.laboratorio3.Logic.Users
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import android.util.Log
import java.net.IDN

/**
 * Created by Eyehunt Team on 07/06/18.
 */
class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {



    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $NOMBRE_COMPLETO TEXT, $TELEFONO TEXT,$CORREO TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Called when the database needs to be upgraded
    }
    fun onUpdate(db:SQLiteDatabase?){

    }

    //Inserting (Creating) data
    fun addUser(user: Users): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOMBRE_COMPLETO, user.nombreCompleto)
        values.put(TELEFONO, user.telefono)
        values.put(CORREO, user.correo)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.d("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun updateUser(posicion: Int,usuario:Users):Boolean{
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(NOMBRE_COMPLETO, usuario.nombreCompleto)
        values.put(TELEFONO, usuario.telefono)
        values.put(CORREO, usuario.correo)
        val _success = db.update(TABLE_NAME,values,ID + "=" + posicion,null)
        db.close()
        Log.v("Se ha editado", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun delUser (posicion: Int):Boolean{
        val db = this.writableDatabase
        //val values = ContentValues()
        //values.put(NOMBRE_COMPLETO, user.nombreCompleto)
        //values.put(TELEFONO, user.telefono)
        //values.put(CORREO, user.correo)
        //val _success = db.delete(TABLE_NAME, null, values)
        val _success = db.delete(TABLE_NAME, ID + "=" + posicion,null)

        db.close()
        Log.v("Se ha borrado", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun getContacto(posicion:Int):Users{

        var user:Users = Users()
        val db = readableDatabase
        val selectALLQuery = "SELECT $ID,$NOMBRE_COMPLETO,$TELEFONO,$CORREO FROM $TABLE_NAME WHERE $ID = $posicion"
        var cursor = db.rawQuery(selectALLQuery, null)
        //val campos = Array<String>(4) {"id";"nombre";"telefono";"correo"}

        //var cursor = db.query(TABLE_NAME, campos, null,null,null,null, NOMBRE_COMPLETO)
        //val cursor = db.quer
        if (cursor != null && cursor.moveToFirst()) {


            user.id = cursor.getColumnIndex(ID)
            user.nombreCompleto = cursor.getString(cursor.getColumnIndex(NOMBRE_COMPLETO))
            user.telefono = cursor.getString(cursor.getColumnIndex(TELEFONO))
            user.correo = cursor.getString(cursor.getColumnIndex(CORREO))

//                    var id = cursor.getString(cursor.getColumnIndex(ID))
//                    var nombreCompleto = cursor.getString(cursor.getColumnIndex(NOMBRE_COMPLETO))
//                    var telefono = cursor.getString(cursor.getColumnIndex(TELEFONO))
//                    var correo = cursor.getString(cursor.getColumnIndex(CORREO))

            //misContactos.add(user)
            //allUser = "$allUser\n$id $nombreCompleto $telefono $correo"

        }
        else
        {}
        cursor.close()
        db.close()
        return user
    }

    //get all users
    fun getAllUsers(): ArrayList<Users> {
        //var allUser: String = "";
        var misContactos = ArrayList<Users>()
        var user:Users = Users()

        misContactos.clear()

        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cursor.moveToFirst()
                do {


                    user.id = cursor.getColumnIndex(ID)
                    user.nombreCompleto = cursor.getString(cursor.getColumnIndex(NOMBRE_COMPLETO))
                    user.telefono = cursor.getString(cursor.getColumnIndex(TELEFONO))
                    user.correo = cursor.getString(cursor.getColumnIndex(CORREO))

//                    var id = cursor.getString(cursor.getColumnIndex(ID))
//                    var nombreCompleto = cursor.getString(cursor.getColumnIndex(NOMBRE_COMPLETO))
//                    var telefono = cursor.getString(cursor.getColumnIndex(TELEFONO))
//                    var correo = cursor.getString(cursor.getColumnIndex(CORREO))

                    misContactos.add(user)
                    //allUser = "$allUser\n$id $nombreCompleto $telefono $correo"
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return misContactos
    }

    companion object {
        private val DB_NAME = "contactosDB"
        private val DB_VERSIOM = 1;
        private val TABLE_NAME = "contactos"
        private val ID = "id"
        private val NOMBRE_COMPLETO = "NombreCompleto"
        private val TELEFONO = "Telfono"
        private val CORREO = "Correo"
    }
}