package com.lazyxu.network.manager

import okhttp3.OkHttpClient
import retrofit2.Retrofit


abstract class BaseNetworkApi {


    abstract fun provideOkHttpClient(): OkHttpClient

    abstract fun provideRetrofit(okHttpClient: OkHttpClient,baseUrl: String): Retrofit

    abstract fun <T> provideApiService(retrofit: Retrofit,serviceClass: Class<T>): T
}



