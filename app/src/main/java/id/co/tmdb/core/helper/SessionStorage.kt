package id.co.tmdb.core.helper

import android.content.Context
import com.orhanobut.hawk.Hawk

class SessionStorage(var context: Context) {
    fun <T> put(key: String, value: T) {
        Hawk.put(key, value)
    }

    fun <T> get(key: String): T? {
        return Hawk.get<T>(key)
    }
}