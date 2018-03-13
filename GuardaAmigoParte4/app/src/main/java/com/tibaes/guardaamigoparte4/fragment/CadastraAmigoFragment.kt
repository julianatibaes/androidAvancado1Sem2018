package com.tibaes.guardaamigoparte4.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.tibaes.guardaamigoparte4.R
import com.tibaes.guardaamigoparte4.ui.CadastraAmigoActivity
import com.tibaes.guardaamigoparte4.ui.ListaAmigosActivity
import com.tibaes.guardaamigos.model.Amigo
import com.tibaes.guardaamigosparte4.dao.AmigoDao
import kotlinx.android.synthetic.main.activity_lista_amigos.*
import kotlinx.android.synthetic.main.fragment_cadastra_amigo.*


/**
 * A simple [Fragment] subclass.
 */
class CadastraAmigoFragment : Fragment() {

    var duasTelas:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_cadastra_amigo, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        return inflater!!.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        when(id){
            R.id.salva_amigo -> {
                salvaAmigo()
                return true
                }
            }
        return super.onOptionsItemSelected(item)
    }

    private fun salvaAmigo() {

        AmigoDao(context).insere(Amigo(
                nome = cadastro_amigo_nome?.text.toString(),
                telefone = cadastro_amigo_tel?.text.toString(),
                email = cadastro_amigo_email?.text.toString(),
                endereco = cadastro_amigo_endereco?.text.toString()
        ))
        Toast.makeText(context, "Amigo salvo - ", Toast.LENGTH_SHORT).show()
        if(!duasTelas){
            startActivity(Intent(context, ListaAmigosActivity::class.java) )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frag = fragment_cadastra_amigo_large
        duasTelas = frag != null

    }
}
