package tibaes.com.guardaamigoretrofit.monitoramento

import com.google.gson.annotations.SerializedName

/**
 * Created by juliana on 26/03/18.
 */
class MonitoramentoModel (@SerializedName("Quantidade de máquinas") var qtdMaquinas: String = "",
                          @SerializedName("Quantidade de pontos") var qtdPontos: String = "",
                          @SerializedName("Estado") var estado: String = "",
                          @SerializedName("Nome da cidade") var cidade: String = "")