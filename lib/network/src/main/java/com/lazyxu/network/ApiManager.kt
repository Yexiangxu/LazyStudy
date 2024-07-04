package com.lazyxu.network

import com.lazyxu.network.manager.NetworkApi
import com.lazyxu.network.service.ApiService


/**
 * User:Lazy_xu
 * Date:2024/06/12
 * Description:
 * FIXME
 */

object ApiManager {
    val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        NetworkApi.getApi(ApiService::class.java, ApiService.SERVER_URL)
    }
}