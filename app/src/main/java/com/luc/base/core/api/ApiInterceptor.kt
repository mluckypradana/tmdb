package com.luc.base.core.api

import com.luc.base.core.Constant
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", Constant.API_KEY)
            .addQueryParameter("language", Constant.LANGUAGE)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}