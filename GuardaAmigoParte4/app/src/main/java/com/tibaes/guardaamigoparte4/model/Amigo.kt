package com.tibaes.guardaamigos.model

import java.io.Serializable
import java.util.*

/**
 * Created by juliana on 17/02/2018.
 */
data class Amigo(val nome:String,
            val telefone:String,
            val src_foto: String = "",
            val dt_nasc: Calendar = Calendar.getInstance(),
            val email: String = "",
            val endereco: String = "",
            val id: Long = 0
            ): Serializable