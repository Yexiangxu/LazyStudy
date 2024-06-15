package com.lazyxu.network

import com.lazyxu.network.ApiResponse.Failure
import com.lazyxu.network.ApiResponse.Success
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Response
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * 密封类:是一种同时拥有枚举类 enum 和 普通类 class 特性的类
 * 注意，密封类及其子类必须声明在同一个 kotlin 文件中
 *
 * 使用密封类的话，when 表达式可以覆盖所有情况，不需要再添加 else 语句
 * 反编译可以看到，[ApiResponse] 类其实是一个抽象类，[Success] 和 [Failure] 继承了这个抽象类
 *
 * 每一个枚举常量值存在一个实例
 * 密封类的一个子类可以有多个状态的实例（eg：获取接口数据失败，可以分为接口请求失败、数据解析失败等）
 *
 */

sealed class ApiResponse<out T> {
    data class Success<T>(val response: Response<T>) : ApiResponse<T>() {
        val data by lazy { response.body() }
        val statusCode: StatusCode = getStatusCodeFromResponse(response)
        val headers: Headers = response.headers()
        val raw: okhttp3.Response = response.raw()
    }

    sealed class Failure<T> : ApiResponse<T>() {
        data class ServerError<T>(val response: Response<T>) : Failure<T>() {
            val statusCode: StatusCode = getStatusCodeFromResponse(response)
            val headers: Headers = response.headers()
            val raw: okhttp3.Response = response.raw()
            val errorBody: ResponseBody? = response.errorBody()
        }

        data class Exception<T>(val exception: Throwable) : Failure<T>()
    }

    public fun <T> getStatusCodeFromResponse(response: Response<T>): StatusCode {
        return StatusCode.values().find { it.code == response.code() }
            ?: StatusCode.Unknown
    }
}

/**
 * 网络请求失败（包括[isSuccess]和[isSuccess]两种情况）
 * Contract是契约,事实上去掉contract语句，该函数的功能并不受任何影响。加上它，只是方便编译器理解函数的行为
 */
@OptIn(ExperimentalContracts::class)
fun <T> ApiResponse<T>.isSuccess(): Boolean {
    //contract代码表明当implies后的值成立，函数将会返回returns函数中的内容，注意这里implies是一个中缀运算符
    contract {
        returns(true) implies (this@isSuccess is ApiResponse.Success)
    }
    return this is ApiResponse.Success
}


@JvmSynthetic//只给kotlin调用不给java调用
suspend inline fun <T> ApiResponse<T>.suspendOnSuccess(crossinline onResult: suspend ApiResponse.Success<T>.() -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        onResult(this)
    }
    return this
}

@JvmSynthetic
inline fun <T> ApiResponse<T>.onError(
    crossinline onResult: ApiResponse.Failure.ServerError<T>.() -> Unit,
): ApiResponse<T> {
    if (this is ApiResponse.Failure.ServerError) {
        onResult(this)
    }
    return this
}

@JvmSynthetic
inline fun <T> ApiResponse<T>.onException(
    crossinline onResult: ApiResponse.Failure.Exception<T>.() -> Unit,
): ApiResponse<T> {
    if (this is ApiResponse.Failure.Exception) {
        onResult(this)
    }
    return this
}