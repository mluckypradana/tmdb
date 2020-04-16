package com.luc.base.core.helper

import android.content.Context
import com.luc.base.App
import com.luc.base.core.extension.filterEmpty
import com.orhanobut.hawk.Hawk

class SessionStorage(var context: Context) {
    fun <T> put(key: String, value: T) {
        Hawk.put(key, value)
    }

    fun <T> get(key: String): T? {
        return Hawk.get<T>(key)
    }
}