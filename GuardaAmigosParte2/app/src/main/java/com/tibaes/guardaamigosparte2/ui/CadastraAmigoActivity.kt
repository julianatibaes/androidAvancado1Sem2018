package com.tibaes.guardaamigosparte2.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.tibaes.guardaamigosparte2.R

class CadastraAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra_amigo)

        // O ? indica que pode ser - tem chances - de o objeto vir vazio
        // nesse caso, está substituindo o uso do try/ catch pra evitar estouro de erro "na cara" do usuário
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        val id = item.itemId
        when(id){
            R.id.salva_amigo -> {
                salvaAmigo()
                startActivity(Intent(this, ListaAmigosActivity::class.java))
                return true
            }

            // observe que o id.home veio originalmente do pacote android.
            // esse id é bem específico para utilizarmos pra usar a seta do botão voltar.
            // isso já é definido pela ferramenta para facilitar a nossa vida
            android.R.id.home ->{
                startActivity(Intent(this, ListaAmigosActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salvaAmigo() {
        Toast.makeText(this, "Amigo salvo - ", Toast.LENGTH_SHORT).show()
    }
}
