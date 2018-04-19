package tibaes.com.guardaamigoretrofit.model

import java.io.Serializable

/**
 * Created by juliana on 25/03/18.
 */

class Amigo(var nome:String = "",
    var telefone:String = "",
    var src_foto: String = "",
    var email: String = "",
    var endereco: String = "",
    var id: String = ""):Serializable
