package id.co.tmdb.core.extension

import com.google.gson.Gson

fun <T> T.json(): String = Gson().toJson(this)
fun <T> String.toGson(clazz: Class<T>): T = Gson().fromJson(this, clazz)