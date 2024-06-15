package com.lazyxu.lazystudy.network

/**
 * 使用sealed
 *
 */
sealed class ResponseResult<out T> {
    data class Success<out T>(val value: T) : ResponseResult<T>()

    data class Failure(val throwable: Throwable?) : ResponseResult<Nothing>()
}

inline fun <reified T> ResponseResult<T>.doSuccess(success: (T) -> Unit) {
    if (this is ResponseResult.Success) {
        success(value)
    }
}

inline fun <reified T> ResponseResult<T>.doFailure(failure: (Throwable?) -> Unit) {
    if (this is ResponseResult.Failure) {
        failure(throwable)
    }
}

