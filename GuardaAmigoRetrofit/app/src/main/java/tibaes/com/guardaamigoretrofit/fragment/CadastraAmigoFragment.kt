package tibaes.com.guardaamigoretrofit.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tibaes.com.guardaamigoretrofit.R


/**
 * A simple [Fragment] subclass.
 */
class CadastraAmigoFragment : Fragment() {

    var duasTelas:Boolean = false
    lateinit var listaAmigosRecycler: RecyclerView


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_cadastra_amigo, container, false)
    }

}// Required empty public constructor
