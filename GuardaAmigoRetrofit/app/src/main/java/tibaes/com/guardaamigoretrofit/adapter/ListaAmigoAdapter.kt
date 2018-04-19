package tibaes.com.guardaamigoretrofit.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.item_lista_amigo.view.*
import tibaes.com.guardaamigoretrofit.ui.CadastraAmigoActivity
import tibaes.com.guardaamigoretrofit.R
import tibaes.com.guardaamigoretrofit.model.Amigo;

/**
 * Created by juliana on 25/03/18.
 */
class ListaAmigoAdapter (val doisFragmentos: Boolean,
                         private val amigos:List<Amigo>,
                         private val context: Context): RecyclerView.Adapter<ListaAmigoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_amigo, parent, false)
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