package com.tibaes.guardaamigoparte6.model

import java.io.Serializable
import java.util.*
/**
 * Created by julia on 18/03/2018.
 */
class Amigo(var nome:String = "",
            var telefone:String = "",
            var src_foto: String = "",
            var email: String = "",
            var endereco: String = "",
            var id: String = ""): Serializable
   /* constructor()

    constructor( nome:String,
     telefone:String,
     src_foto: String = "",
    //  val dt_nasc: Calendar = Calendar.getInstance(),
     email: String = "",
     endereco: String = "",
     id: String = ""



    var nome:String = ""
    var telefone:String = ""
    val src_foto: String = ""
    //  val dt_nasc: Calendar = Calendar.getInstance(),
    val email: String = ""
    val endereco: String = ""
    var id: String = ""
}
*/