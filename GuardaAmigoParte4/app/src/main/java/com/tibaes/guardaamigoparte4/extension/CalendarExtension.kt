package com.tibaes.guardaamigosparte4.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by juliana on 18/02/2018.
 */

fun Calendar.formataParaBrasileiro(): String {
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}

