package com.tibaes.guardaamigoparte2.model

import java.util.*

/**
 * Created by juliana on 17/02/2018.
 */
class Amigo(val nome:String,
            val telefone:String,
            val src_foto: String = "",
            val dt_nasc: Calendar = Calendar.getInstance(),
            val email: String = "",
            val endereco: String = "",
            val id: Long = 0
            )