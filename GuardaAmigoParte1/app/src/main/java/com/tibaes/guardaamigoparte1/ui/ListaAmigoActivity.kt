package com.tibaes.guardaamigoparte1.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager

import com.tibaes.guardaamigoparte1.R
import com.tibaes.guardaamigoparte1.adapter.ListaAmigoAdapter
import com.tibaes.guardaamigoparte1.model.Amigo
import kotlinx.android.synthetic.main.activity_lista_amigo.*

class ListaAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_amigo)

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

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        listaAmigosRecycler.layoutManager = layoutManager
    }

    private fun listaAmigos(): List<Amigo> {
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
