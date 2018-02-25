package com.tibaes.guardaamigoparte2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter

import kotlinx.android.synthetic.main.item_lista_amigos.view.*
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import com.tibaes.guardaamigoparte2.model.Amigo
import com.tibaes.guardaamigosparte2.R

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

        // if (holder != null) {
        // let está permitindo rodar um bloco de código (semelhante ao do)
        // caso o holder não for vazio (?)
        holder?.let{
            // it está substituindo a chamada do holder
            it.nome.text = amigo.nome
            it.telefone.text = amigo.telefone

            it.itemView.setOnClickListener(){

                // o PopUp é uma biblioteca utilizada para fazermos menus flutuantes
                // nesse caso, cada vez que eu clico no item da lista, ele aparece um menu com as opções
                val popUp = PopupMenu(context, holder.itemView)
                popUp.inflate(R.menu.menu_item)
                popUp.setOnMenuItemClickListener{

                    when(it.itemId){
                        R.id.menu_excluir->{
                            Toast.makeText(context, "EXCLUIR", Toast.LENGTH_SHORT).show()
                            true
                        }
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


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        // agora não precisamos mais do findViewById e aquela montueira de código
        // basta uilizar a biblioteca do kotlin, syntetic, e dizer que a sua variável irá receber
        // o item do layout (basta informar o id do item de view)
        val nome = itemView.amigo_item_nome
        val telefone = itemView.amigo_item_tel

    }
}

