package com.tibaes.guardaamigoparte6.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tibaes.guardaamigoparte6.R

class CadastraAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        val id = item.itemId
        when(id){
            android.R.id.home ->{
                //startActivity(Intent(this, ListaAmigosActivity::class.java))
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
