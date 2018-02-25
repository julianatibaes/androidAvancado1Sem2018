package com.tibaes.guardaamigosparte2.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.os.Bundle


import com.tibaes.guardaamigoparte2.adapter.ListaAmigoAdapter
import com.tibaes.guardaamigoparte2.model.Amigo
import com.tibaes.guardaamigosparte2.R
import kotlinx.android.synthetic.main.activity_lista_amigos.*

class ListaAmigosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_amigos)

        /**
         * dê adeus ao findViewById
         * var addAmigo = findViewById<FloatingActionButton>(R.id.lista_add_amigo)
         * addAmigo.setOnClickListener{
         *  startActivity(Intent(this, CadastraAmigoActivity::class.java))
         *  }
         *
         *  Adicione a lib import kotlinx.android.synthetic.main.NOME_DO_LAYOUT.*
         */

        lista_add_amigo.setOnClickListener{
            startActivity(Intent(this, CadastraAmigoActivity::class.java))
        }

        val listaAmigosRecycler = lista_amigos_recycler
        listaAmigosRecycler.adapter = ListaAmigoAdapter(listaAmigos(), this)

        //lembrem que o spanCount define o número de colunas no grid. Vai ser muito útil para
        //trabalharmos com listas com layout variando de acordo com a tela
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        listaAmigosRecycler.layoutManager = layoutManager
    }

    private fun listaAmigos(): List<Amigo> {

        // o listOf define uma lista de objetos. Nesse caso, o objeto Amigo. Repare que eu não uso mais o new
        return listOf(
                Amigo("Max", "(41) 9999-9999"),
                Amigo("El", "(42) 9999-9998"),
                Amigo("Dustin", "(41) 9999-9997"),
                Amigo("Mike", "(41) 9999-9996"),
                Amigo("Lucas", "(41) 9999-9995"),
                Amigo("Will", "(41) 9999-9994"),
                Amigo("Steve", "(41) 9999-9993"),
                Amigo("Nancy", "(41) 9999-9992"),
                Amigo("Jonathan", "(41) 9999-9991")
        )
    }
}
