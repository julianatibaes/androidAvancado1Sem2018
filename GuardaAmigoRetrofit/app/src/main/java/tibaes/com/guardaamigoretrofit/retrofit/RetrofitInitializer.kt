package tibaes.com.guardaamigoretrofit.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tibaes.com.guardaamigoretrofit.retrofit.service.AmigoService

/**
 * Created by juliana on 02/04/18.
 */
class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://172.17.3.88:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun amigoService() = retrofit.create(AmigoService::class.java)
}