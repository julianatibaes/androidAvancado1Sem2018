package com.tibaes.guardaamigoparte1.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tibaes.guardaamigoparte1.R

class CadastraAmigoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra_amigo)
    }

    private fun salvaAmigo() {
        Toast.makeText(this, "Amigo salvo - ", Toast.LENGTH_SHORT).show()
    }
}
