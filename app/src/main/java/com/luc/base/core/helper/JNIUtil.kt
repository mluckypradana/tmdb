package com.luc.base.core.helper

object JNIUtil {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiEndpoint(): String

    fun imageEndPoint() = "https://image.tmdb.org/t/p/w200"
}