package com.example.smartdam.util.base.resource

sealed class Resource<T>(val data: T? = null, val error: ErrorModel? = null) {
    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(error: ErrorModel? = null) : Resource<T>(error = error)

    class Loading<T> : Resource<T>()

    class Nothing<T> : Resource<T>()
}