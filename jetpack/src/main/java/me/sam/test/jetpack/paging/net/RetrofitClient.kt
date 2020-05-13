package me.sam.test.jetpack.paging.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * desc: Retrofit Client
 * date: 2020/05/12 0012
 * @author: dongsen
 */
object RetrofitClient {

    // https://api.thecatapi.com/v1/images/search?limit=5&page=10&order=Desc
    private const val BASE_URL: String = "https://api.thecatapi.com"

    private const val CONNECT_TIMEOUT: Long = 16
    private const val WRITE_TIMEOUT: Long = 60
    private const val READ_TIMEOUT: Long = 60

    val instance: ApiStore by lazy {
        createRetrofitClient().create(ApiStore::class.java)
    }

    private fun createOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor { message ->
                    Log.d("Client", message)
                }.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    private fun createRetrofitClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}