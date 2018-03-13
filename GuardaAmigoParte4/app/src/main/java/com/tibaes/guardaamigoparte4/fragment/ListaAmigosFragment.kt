package com.tibaes.guardaamigoparte4.fragment


import android.os.Bundle
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import com.tibaes.guardaamigoparte4.R
import com.tibaes.guardaamigoparte4.ui.CadastraAmigoActivity
import com.tibaes.guardaamigos.model.Amigo
import com.tibaes.guardaamigosparte4.adapter.ListaAmigoAdapter
import com.tibaes.guardaamigosparte4.dao.AmigoDao
import kotlinx.android.synthetic.main.activity_lista_amigos.*

/**
 * A simple [Fragment] subclass.
 */
class ListaAmigosFragment : Fragment() {

    var duasTelas:Boolean = false
    lateinit var listaAmigosRecycler: RecyclerView
    var lista:ArrayList<Amigo> = ArrayList<Amigo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val inf = inflater!!.inflate(R.layout.fragment_lista_amigos, container, false)

        // findViewById de volta na área porque ainda não está muito estável esse tal do kotlin
        // como estamos lidando com um objeto dentro de outro, precisamos da referência da view do
        // fragmento para que o objeto seja encontrado e manipulado. Logo, logo isso deve mudar.

        // não se esqueça de que todos os this, passam a ser context. Afinal, não vamos executar na
        // mesma classe a função.

        val addAmigo = inf.findViewById<FloatingActionButton>(R.id.lista_add_amigo)
        addAmigo.setOnClickListener {
            if(duasTelas!=true){
                startActivity(Intent(context, CadastraAmigoActivity::class.java))
            }
        }

        listaAmigosRecycler = inf.findViewById<RecyclerView>(R.id.lista_amigos_recycler)

        return inf
    }

    private fun montaListaRecycler(listaAmigosRecycler: RecyclerView) {
        listaAmigosRecycler.let {
            it.adapter = ListaAmigoAdapter(duasTelas, listaAmigos(), context)
            val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            it.layoutManager = layoutManager
        }
        // ativar a ação quando move o item para a direita ou para a esquerda
        setRecyclerVewItemTouchListener()
    }

    private fun listaAmigos(): List<Amigo> {
        lista = AmigoDao(context).lista() as ArrayList<Amigo>
        return lista
    }


    override fun onResume() {
        montaListaRecycler(listaAmigosRecycler)
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val frag = fragment_cadastra_amigo_large
        duasTelas = frag != null
    }

    private fun setRecyclerVewItemTouchListener() {
        val itemTouchCallback = object :
                ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT or
                                ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                //2
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                val position = viewHolder!!.adapterPosition
                AmigoDao(context).exclui(lista.get(position))
                lista.removeAt(position)
                listaAmigosRecycler.adapter.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(listaAmigosRecycler)
    }
}