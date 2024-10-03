package com.example.navigationcomponentdemo.apisetup


sealed class BGStateHolder<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : BGStateHolder<T>(data = data)
    class Error<T>(errorMessage: String) : BGStateHolder<T>(message = errorMessage)
    class Loading<T> : BGStateHolder<T>()
    class Empty<T> : BGStateHolder<T>()
}