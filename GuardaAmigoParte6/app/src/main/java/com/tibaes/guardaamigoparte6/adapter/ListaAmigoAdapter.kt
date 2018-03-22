package com.tibaes.guardaamigoparte6.adapter

import android.view.View

import com.tibaes.guardaamigoparte6.model.Amigo
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.tibaes.guardaamigoparte6.R
import com.tibaes.guardaamigoparte6.fragment.CadastraAmigoFragment
import com.tibaes.guardaamigoparte6.ui.CadastraAmigoActivity
import kotlinx.android.synthetic.main.item_lista_amigos.view.*
import android.support.v4.content.ContextCompat.startActivity



/**
 * Created by julia on 18/03/2018.
 */
class ListaAmigoAdapter(val doisFragmentos: Boolean,
                        private val amigos:List<Amigo>,
                        private val context:Context): Adapter<ListaAmigoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_amigos, parent, false)
        return ViewHolder(view)
    }

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

            it.itemView.setOnLongClickListener({
                Toast.makeText(context,
                        "Abrir item",
                        Toast.LENGTH_SHORT).show()
                if(!doisFragmentos){
                    val intent = Intent(context, CadastraAmigoActivity::class.java)
                    intent.putExtra("goAmigo", amigo)
                    context.startActivity(intent)
                }
                false
            })
        }
    }

    override fun getItemCount(): Int {
        return amigos.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val nome = itemView.amigo_item_nome
        val telefone = itemView.amigo_item_tel
    }


}