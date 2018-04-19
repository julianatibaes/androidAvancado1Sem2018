package tibaes.com.guardaamigoretrofit.retrofit.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tibaes.com.guardaamigoretrofit.model.Amigo

/**
 * Created by juliana on 02/04/18.
 */
interface AmigoService {

    @GET("amigo")
    fun list(): Call<List<Amigo>>

    @POST("amigo")
    fun insert(@Body amigo: Amigo): Call<Amigo>
}