package id.co.tmdb.core.retrofit

import id.co.tmdb.core.helper.JNIUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitFactory {

    fun retrofitService(): ApiService {

        return Retrofit.Builder()
            .baseUrl(JNIUtil.apiEndpoint())
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    private fun okHttpClient(): OkHttpClient {

        val okHttpBuild = OkHttpClient.Builder()
        okHttpBuild.connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuild.readTimeout(30, TimeUnit.SECONDS)
        okHttpBuild.writeTimeout(30, TimeUnit.SECONDS)
        okHttpBuild.addInterceptor(ApiInterceptor())
        return okHttpBuild.build()
    }
}