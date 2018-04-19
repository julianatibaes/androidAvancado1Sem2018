package tibaes.com.guardaamigoretrofit.retrofit.client

import tibaes.com.guardaamigoretrofit.model.Amigo
import tibaes.com.guardaamigoretrofit.retrofit.RetrofitInitializer
import tibaes.com.guardaamigoretrofit.retrofit.callback

/**
 * Created by juliana on 02/04/18.
 */
class AmigoClient {

    fun list(success: (notes: List<Amigo>) -> Unit,
             failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().amigoService().list()
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

    fun insert(amigo: Amigo, success: (amigo: Amigo) -> Unit,
               failure: (throwable: Throwable) -> Unit) {
        val call = RetrofitInitializer().amigoService().insert(amigo)
        call.enqueue(callback({ response ->
            response?.body()?.let {
                success(it)
            }
        }, { throwable ->
            throwable?.let {
                failure(it)
            }
        }))
    }

}