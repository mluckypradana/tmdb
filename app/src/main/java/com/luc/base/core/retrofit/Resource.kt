package com.luc.base.core.retrofit

// A generic class that contains message and status about loading this message.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message)
    class Error<T>(data: T? = null, message: String? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class Empty<T> : Resource<T>()
}
 