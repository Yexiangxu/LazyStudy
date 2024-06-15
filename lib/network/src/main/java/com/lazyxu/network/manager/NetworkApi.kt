package com.lazyxu.network.manager

import com.google.gson.GsonBuilder
import com.lazyxu.base.utils.BuildConfigs
import com.lazyxu.network.interceptor.PublicParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


internal object NetworkApi : BaseNetworkApi() {
    fun <T> getApi(serviceClass: Class<T>, baseUrl: String): T {
        return provideApiService(provideRetrofit(provideOkHttpClient(), baseUrl), serviceClass)
    }

    /**
     * TODO addNetworkInterceptor和addInterceptor区别详解 https://juejin.cn/post/7151761448757264415
     */
    override fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(PublicParamsInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfigs.IS_DEV) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
            //超时时间 连接、读、写
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    override fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    override fun <T> provideApiService(retrofit: Retrofit, serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}



