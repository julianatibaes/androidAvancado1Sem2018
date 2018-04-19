package tibaes.com.guardaamigoretrofit.monitoramento

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by juliana on 26/03/18.
 */
interface MonitoramentoService {

    @GET("json?metrics=Quantidade%20de%20máquinas%2CQuantidade%20de%20pontos&dimensions=Estado%2CNome%20da%20cidade&filters=Ativo%3D%3Dtrue%3BEstado%3D%3DPR")
    fun listaParana(): Call<List<MonitoramentoModel>>
}