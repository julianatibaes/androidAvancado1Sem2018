package tibaes.com.guardaamigoretrofit.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_cadastro_amigo.view.*
import tibaes.com.guardaamigoretrofit.R
import tibaes.com.guardaamigoretrofit.model.Amigo
import tibaes.com.guardaamigoretrofit.retrofit.client.AmigoClient

/**
 * Created by juliana on 02/04/18.
 */
class CadastraAmigoDialog(private val viewGroup: ViewGroup,
                          private val context: Context) {

    fun show(created: (criaAmigo: Amigo) -> Unit) {
        val createdView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_cadastro_amigo,
                        viewGroup,
                        false)


        AlertDialog.Builder(context)
                .setTitle("Add note")
                .setView(createdView)
                .setPositiveButton("Save") { _, _ ->
                    val nome = createdView.dialog_cadastro_amigo_nome.text.toString()
                    val telefone = createdView.dialog_cadastro_amigo_email.text.toString()
                    val note = Amigo(nome = nome, telefone = telefone)
                    AmigoClient().insert(note, {
                        created(it)
                    }, {
                        Toast.makeText(context, "Falha ao salvar", Toast.LENGTH_LONG).show()
                    })
                }
                .show()
    }
}