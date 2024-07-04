package com.lazyxu.network.interceptor

import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.NetUtils
import com.lazyxu.network.error.ApiException
import com.lazyxu.network.error.NoNetWorkException
import com.lazyxu.network.error.StatusCode
import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        LogUtils.d(LogTag.HTTP, request.toString())
        if (NetUtils.isConnected(BaseApplication.INSTANCE)) {
            return chain.proceed(request)
        } else {
            throw NoNetWorkException(StatusCode.NETWORD_ERROR)
        }
    }
}
