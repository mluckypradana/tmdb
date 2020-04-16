package id.co.tmdb.core.retrofit

import id.co.tmdb.core.Constant
import id.co.tmdb.core.helper.JNIUtil
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", JNIUtil.apiKey())
            .addQueryParameter("language", Constant.LANGUAGE)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}