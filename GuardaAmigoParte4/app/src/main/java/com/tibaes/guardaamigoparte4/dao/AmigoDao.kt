package com.tibaes.guardaamigosparte4.dao

import android.content.Context

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.tibaes.guardaamigos.model.Amigo
import com.tibaes.guardaamigosparte4.data.DBManager
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by julia on 26/02/2018.
 */
class AmigoDao (context: Context): DBManager(context) {

    fun insere(amigo: Amigo) {
        val db = writableDatabase
        val dados = populaDados(amigo)
        db.insert(dbTable, null, dados)
    }

    fun altera(amigo: Amigo) {
        val db = writableDatabase
        val dados = populaDados(amigo)
        db.update(dbTable, dados, colId + "= ?", arrayOf(amigo.id.toString()))
    }

    fun exclui(amigo: Amigo) {
        val db = writableDatabase
        db.delete(dbTable, colId + "= ?", arrayOf(amigo.id.toString()))
    }

    fun lista():List<Amigo> {
        val sql = "SELECT * FROM " + dbTable + ";"
        val db = readableDatabase

        val c: Cursor = db.rawQuery(sql, null)
        val amigos = ArrayList<Amigo>()
        if (c.moveToFirst()) {
            do {
                amigos.add(Amigo(
                        id = c.getLong(c.getColumnIndex(colId)),
                        nome = c.getString(c.getColumnIndex(colNome)),
                        telefone = c.getString(c.getColumnIndex(colTelefone))
                       /* c.getString(c.getColumnIndex(colFoto)),
                        formataBrasileiroParaCalendar(c.getString(c.getColumnIndex(colDtNasc))),
                        c.getString(c.getColumnIndex(colEmail)),
                        c.getString(c.getColumnIndex(colEndereco)),
                        (c.getInt(c.getColumnIndex(colId))).toLong() */
                ))
            } while (c.moveToNext())
            c.close()
        }
        return amigos
    }

    private fun populaDados(amigo: Amigo): ContentValues {
        val dados = ContentValues()
        dados.put(colNome, amigo.nome)
        dados.put(colEmail, amigo.email)
        dados.put(colEndereco, amigo.endereco)
        dados.put(colFoto, amigo.src_foto)
        dados.put(colTelefone, amigo.telefone)
        return dados
    }

    private fun formataBrasileiroParaCalendar(data: String): Calendar {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        cal.setTime(sdf.parse(data))
        return cal
    }
}