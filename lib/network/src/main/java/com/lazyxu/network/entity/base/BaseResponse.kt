package com.lazyxu.network.entity.base


data class BaseResponse<T>(
    var data: T? = null,//兼容后端同一个接口返回null等，还经常遇到同一个接口有数据返回对象和没数据返回数组等傻逼情况
    var errorCode: Int,
    var errorMsg: String
) {
    val isSuccess: Boolean
        get() = errorCode == 0
}