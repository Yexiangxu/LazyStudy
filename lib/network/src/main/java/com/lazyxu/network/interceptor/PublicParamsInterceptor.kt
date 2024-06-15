package com.lazyxu.network.interceptor

import com.lazyxu.base.utils.DeviceUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder

/**
 * 封装头信息里面的统一参数
 * 或者在url上面加sign
 */
internal class PublicParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .header("platform", "android")
                .header("ver", DeviceUtil.VERSION_NAME)
                .header("qid", "") //渠道号
                .header("oaid", "") //设备唯一标志
                .header("ClientUpdateTime", System.currentTimeMillis().toString())//当前请求时间
                .header("device-brand", URLEncoder.encode(DeviceUtil.BRAND, "UTF-8"))//品牌
                .header("device-model", URLEncoder.encode(DeviceUtil.MODLE, "UTF-8"))//机型
                .header("device-os", URLEncoder.encode(DeviceUtil.DEVICE_OS, "UTF-8"))//获取手机系统版本号
                .build().newBuilder().build()
        )
    }
}