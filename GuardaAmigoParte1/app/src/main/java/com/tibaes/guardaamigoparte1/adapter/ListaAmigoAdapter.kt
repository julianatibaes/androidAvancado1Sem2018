package com.tibaes.guardaamigoparte1.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import com.tibaes.guardaamigoparte1.R

import com.tibaes.guardaamigoparte1.model.Amigo
import kotlinx.android.synthetic.main.item_lista_amigos.view.*
import android.view.*

/**
 * Created by julia on 17/02/2018.
 */
class ListaAmigoAdapter(private val amigos:List<Amigo>,
                        private val context:Context): Adapter<ListaAmigoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_amigos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return amigos.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val amigo = amigos[position]
        if (holder != null) {
            holder.nome.text = amigo.nome
            holder.telefone.text = amigo.telefone

        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nome = itemView.amigo_item_nome
        val telefone = itemView.amigo_item_tel

    }
}

