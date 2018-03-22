package com.tibaes.guardaamigoparte6.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tibaes.guardaamigoparte6.R
import com.tibaes.guardaamigoparte6.model.Amigo
import kotlinx.android.synthetic.main.activity_lista_amigos.*
import kotlinx.android.synthetic.main.fragment_cadastra_amigo.*
import kotlinx.android.synthetic.main.fragment_cadastra_amigo.view.*

class CadastraAmigoFragment : Fragment() {

    var duasTelas:Boolean = false
    var idAmigo: String = ""

    // adicionando o Firebase
    lateinit var database: FirebaseDatabase
    lateinit var myRef: DatabaseReference
    val MESSAGES_CHILD = "amigos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        // novo
        database = FirebaseDatabase.getInstance()
        myRef = database.getReference()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_cadastra_amigo, container, false)

        val amigoIntent: Intent? = getActivity().getIntent()
        if (amigoIntent?.extras != null){
            val amigoPopula: Amigo? =  amigoIntent.getSerializableExtra("goAmigo") as Amigo
            amigoPopula.let {
                view.cadastro_amigo_nome.setText(it?.nome)
                view.cadastro_amigo_email.setText(it?.email)
                view.cadastro_amigo_tel.setText(it?.telefone)
                view.cadastro_amigo_endereco.setText(it?.endereco)
                idAmigo = it?.id.toString()
                view.cadastro_amigo_id.setText(it?.id)
            }
        }
        return view
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

        if(idAmigo.equals("")){
            val id = myRef.child(MESSAGES_CHILD).push().key

            val amigoEnvia = Amigo(nome = cadastro_amigo_nome.text.toString(),
                    telefone = cadastro_amigo_tel.text.toString(),
                    email = cadastro_amigo_email.text.toString(),
                    endereco = cadastro_amigo_endereco.text.toString(),
                    id = id)
            myRef.child(MESSAGES_CHILD).push().setValue(amigoEnvia)
        } else{
            val amigoEnvia = Amigo(nome = cadastro_amigo_nome.text.toString(),
                    telefone = cadastro_amigo_tel.text.toString(),
                    email = cadastro_amigo_email.text.toString(),
                    endereco = cadastro_amigo_endereco.text.toString(),
                    id = idAmigo)
            myRef.child(MESSAGES_CHILD)
                    .child(idAmigo)
                    .setValue(amigoEnvia)
        }
        if(!duasTelas){
            activity.finish()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frag = fragment_cadastra_amigo_large
        duasTelas = frag != null
    }
}// Required empty public constructor
