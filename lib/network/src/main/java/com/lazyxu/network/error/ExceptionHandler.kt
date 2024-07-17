package com.lazyxu.network.error

import android.net.ParseException
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * 统一错误处理工具类
 */
object ExceptionHandler {

    fun handleException(e: Throwable): ApiException {
        val ex: ApiException
        when (e) {
            is ApiException -> {
                if (e.errCode == StatusCode.UNLOGIN.code) {
                    //登录失效
                }
                ex = e
            }

            is NoNetWorkException -> {
                ex = ApiException(StatusCode.NETWORD_ERROR, e)
            }

            is HttpException -> {
                ex = when (e.code()) {
                    StatusCode.Unauthorized.code -> ApiException(StatusCode.Unauthorized, e)
                    StatusCode.Forbidden.code -> ApiException(StatusCode.Forbidden, e)
                    StatusCode.NotFound.code -> ApiException(StatusCode.NotFound, e)
                    StatusCode.RequestTimeout.code -> ApiException(StatusCode.RequestTimeout, e)
                    StatusCode.GatewayTimeout.code -> ApiException(StatusCode.GatewayTimeout, e)
                    StatusCode.InternalServerError.code -> ApiException(
                        StatusCode.InternalServerError,
                        e
                    )

                    StatusCode.BadGateway.code -> ApiException(StatusCode.BadGateway, e)
                    StatusCode.ServiceUnavailable.code -> ApiException(
                        StatusCode.ServiceUnavailable,
                        e
                    )

                    else -> ApiException(e.code(), e.message(), e)
                }
            }

            is JsonParseException, is JSONException, is ParseException, is MalformedJsonException -> {
                ex = ApiException(StatusCode.PARSE_ERROR, e)
            }

            is ConnectException -> {
                ex = ApiException(StatusCode.NETWORD_ERROR, e)
            }

            is SSLException -> {
                ex = ApiException(StatusCode.SSL_ERROR, e)
            }

            is SocketException -> {
                ex = ApiException(StatusCode.TIMEOUT_ERROR, e)
            }

            is SocketTimeoutException -> {
                ex = ApiException(StatusCode.TIMEOUT_ERROR, e)
            }

            is UnknownHostException -> {
                ex = ApiException(StatusCode.UNKNOW_HOST, e)
            }

            else -> {
                ex = ApiException(StatusCode.UNKNOWN, e)
            }
        }
        return ex
    }
}
