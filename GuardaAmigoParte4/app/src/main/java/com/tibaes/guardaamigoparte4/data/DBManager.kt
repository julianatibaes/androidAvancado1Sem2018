package com.tibaes.guardaamigosparte4.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by julia on 26/02/2018.
 *
 * Esta classe é semelhante ao modo de fazer no Android com Java
 * Como só terei uma tabela e sei que meu projeto não passará disso, é muito mais
 * preciso e elegante deixar em uma única classe.
 *
 */

open class DBManager: SQLiteOpenHelper {

    val dbTable = "Amigos"
    val colId = "ID"
    val colNome = "Nome"
    val colEmail = "Email"
    val colFoto = "Foto"
    val colTelefone = "Telefone"
    val colEndereco = "Endereco"
    val colDtNasc = "DtNascimento"

    constructor(context: Context):super(context, "GuardaAmigos", null, 1)

    /* ficará assim
     val createDatabase = "CREATE TABLE IF NOT EXISTS Amigos (
            ID INTEGER PRIMARY KEY,
            Nome TEXT,
            Email TEXT,
            Endereco TEXT,
            Foto TEXT,
            Telefone TEXT,
            DtNascimento TEXT); "
     */
    val createDatabase = "CREATE TABLE IF NOT EXISTS " + dbTable + " ( " +
            colId + " INTEGER PRIMARY KEY, " +
            colNome + " TEXT, " +
            colEmail + " TEXT, " +
            colEndereco + " TEXT, " +
            colFoto + " TEXT, " +
            colTelefone + " TEXT, " +
            colDtNasc + " TEXT); "

   // inner class DataBaseHelperGuardaAmigos: SQLiteOpenHelper {

     //   constructor(context: Context):super(context, dbName, null, dbVersion)

        override fun onCreate(p0: SQLiteDatabase?) {
            p0!!.execSQL(createDatabase)
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            p0!!.execSQL("DROP TABLE IF EXISTS " + dbTable)
            onCreate(p0)
        }
  //  }
}