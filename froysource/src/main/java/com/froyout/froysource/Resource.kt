package com.froyout.froysource

sealed class Resource<out T>(val data: T? = null, val message: String? = null, val code: Int? = null) {
    class Default: Resource<Nothing>()
    class Success<T>(data: T?): Resource<T>(data)
    class Loading: Resource<Nothing>()
    class Error(message: String? = null, code: Int? = null): Resource<Nothing>(message = message, code = code)
}