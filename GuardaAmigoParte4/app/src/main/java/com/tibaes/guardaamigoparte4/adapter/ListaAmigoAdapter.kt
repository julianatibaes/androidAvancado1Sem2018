package com.tibaes.guardaamigosparte4.adapter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter

import com.tibaes.guardaamigos.model.Amigo
import kotlinx.android.synthetic.main.item_lista_amigos.view.*
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_lista_amigos.*
import android.os.Bundle
import com.tibaes.guardaamigoparte4.R
import com.tibaes.guardaamigoparte4.fragment.CadastraAmigoFragment
import com.tibaes.guardaamigoparte4.fragment.ListaAmigosFragment
import com.tibaes.guardaamigoparte4.ui.CadastraAmigoActivity


/**
 * Created by julia on 17/02/2018.
 */
class ListaAmigoAdapter(val duasTelas: Boolean,
                        private val amigos:List<Amigo>,
                        private val context:Context): Adapter<ListaAmigoAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val amigo = amigos[position]
        holder.let {
            it.nome.text = amigo.nome
            it.telefone.text = amigo.telefone
            it.itemView.setOnClickListener(){
                val popUp = PopupMenu(context, holder.itemView)
                popUp.inflate(R.menu.menu_item)
                popUp.setOnMenuItemClickListener{
                    when(it.itemId){
                        R.id.menu_maps->{
                            Toast.makeText(context, "MAPA", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id.menu_ligar->{
                            Toast.makeText(context, "LIGAR", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else->{
                            false
                        }
                    }
                }
                popUp.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_amigos, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val nome = itemView.amigo_item_nome
        val telefone = itemView.amigo_item_tel
    }

    override fun getItemCount(): Int {
        return amigos.size
    }


}

