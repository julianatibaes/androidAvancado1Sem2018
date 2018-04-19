package tibaes.com.guardaamigoretrofit.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_lista_amigo.*
import tibaes.com.guardaamigoretrofit.R
import tibaes.com.guardaamigoretrofit.adapter.ListaAmigoAdapter
import tibaes.com.guardaamigoretrofit.model.Amigo
import tibaes.com.guardaamigoretrofit.retrofit.AmigoInitializer


/**
 * A simple [Fragment] subclass.
 */
class ListaAmigoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AmigoInitializer()
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_lista_amigo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    private fun montaLista(listaAmigo: List<Amigo>) {
        val recyclerView = lista_amigos_recycler
        recyclerView.adapter = ListaAmigoAdapter(false, listaAmigo, context)
        val layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}// Required empty public constructor





