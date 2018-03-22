package com.tibaes.guardaamigoparte6.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.tibaes.guardaamigoparte6.R
import com.tibaes.guardaamigoparte6.adapter.ListaAmigoAdapter
import com.tibaes.guardaamigoparte6.model.Amigo
import com.tibaes.guardaamigoparte6.ui.CadastraAmigoActivity
import kotlinx.android.synthetic.main.activity_lista_amigos.*
import kotlinx.android.synthetic.main.fragment_lista_amigos.view.*

class ListaAmigosFragment : Fragment() {

    var duasTelas:Boolean = false
    lateinit var listaAmigosRecycler: RecyclerView

    private val listaAamigo: MutableList<Amigo> = mutableListOf()

    // adicionando o Firebase
    lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        myRef = FirebaseDatabase.getInstance().getReference()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inf = inflater!!.inflate(R.layout.fragment_lista_amigos, container, false)

        inf.lista_add_amigo.setOnClickListener {
            if(duasTelas!=true){
                startActivity(Intent(context, CadastraAmigoActivity::class.java))
            }
        }

        listaAmigosRecycler = inf.findViewById<RecyclerView>(R.id.lista_amigos_recycler)
        return inf
    }

    override fun onResume() {
        preparaListaAmigos()
        montaListaRecycler(listaAmigosRecycler)
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val frag = fragment_cadastra_amigo_large
        duasTelas = frag != null
    }

    private fun loadDatabase(firebaseData: DatabaseReference) {
        val availableAmigos: List<Amigo> = mutableListOf(
                Amigo(nome = "Juliana", telefone = "99999999"),
                Amigo(nome = "Heloisa", telefone = "99999998"),
                Amigo(nome = "Martins", telefone =  "99999997"),
                Amigo(nome = "Zelia", telefone = "99999996")
        )
        availableAmigos.forEach {
            val key = firebaseData.child("amigos").push().key
            it.id = key
            firebaseData.child("amigos").child(key).setValue(it)
        }
    }

    private fun preparaListaAmigos() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listaAamigo.clear()
                dataSnapshot.children.mapNotNullTo(listaAamigo) {
                    it.getValue<Amigo>(Amigo::class.java)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        myRef.child("amigos").addListenerForSingleValueEvent(menuListener)
    }

    private fun montaListaRecycler(listaAmigosRecycler: RecyclerView) {

        listaAmigosRecycler.let {
            it.adapter = ListaAmigoAdapter(duasTelas, listaAamigo, context)
            val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            it.layoutManager = layoutManager
        }
        // ativar a ação quando move o item para a direita ou para a esquerda
        setRecyclerVewItemTouchListener()
    }

    private fun setRecyclerVewItemTouchListener() {
        val itemTouchCallback = object :
                ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT or
                                ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                val position = viewHolder!!.adapterPosition
                removeAmigo(position)
                listaAamigo.removeAt(position)
                listaAmigosRecycler.adapter.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(listaAmigosRecycler)
    }

    private fun removeAmigo(position: Int) {
        myRef
                .child("amigos")
                .child(listaAamigo.get(position).id)
                .setValue(null)
    }
}// Required empty public constructor



