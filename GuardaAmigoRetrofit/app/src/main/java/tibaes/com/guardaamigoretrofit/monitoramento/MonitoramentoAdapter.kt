package tibaes.com.guardaamigoretrofit.monitoramento

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_lista_monitoramento.view.*
import tibaes.com.guardaamigoretrofit.R

/**
 * Created by juliana on 26/03/18.
 */
class MonitoramentoAdapter (val listaMonitoramento: List<MonitoramentoModel>,
                                                        private val context: Context):
                                                    RecyclerView.Adapter<MonitoramentoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_lista_monitoramento, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monitoramento = listaMonitoramento[position]
        holder.let {
            it.estado.text = monitoramento.estado
            it.cidade.text = monitoramento.cidade
            it.qtdMaquinas.text = monitoramento.qtdMaquinas
            it.qtdPntos.text = monitoramento.qtdPontos
        } }

    override fun getItemCount(): Int {
        return listaMonitoramento.size
    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val estado = itemView.txtEstado
        val cidade = itemView.txtCidade
        val qtdMaquinas = itemView.txtQtdMaquinas
        val qtdPntos = itemView.txtQtdPontos
    }

}

