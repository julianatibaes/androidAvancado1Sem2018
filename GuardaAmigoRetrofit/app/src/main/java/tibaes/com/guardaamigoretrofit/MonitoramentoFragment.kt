package tibaes.com.guardaamigoretrofit


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_monitoramento.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tibaes.com.guardaamigoretrofit.monitoramento.MonitoramentoAdapter
import tibaes.com.guardaamigoretrofit.monitoramento.MonitoramentoModel
import tibaes.com.guardaamigoretrofit.retrofit.RetrofitInitializer


/**
 * A simple [Fragment] subclass.
 */
class MonitoramentoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RetrofitInitializer()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_monitoramento, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun montaLista(listaMonitoramento: List<MonitoramentoModel>) {
        val recyclerView = recyclerMonitoramento
        recyclerView.adapter = MonitoramentoAdapter(listaMonitoramento, context)
        val layoutManager = StaggeredGridLayoutManager(
                1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}// Required empty public constructor
