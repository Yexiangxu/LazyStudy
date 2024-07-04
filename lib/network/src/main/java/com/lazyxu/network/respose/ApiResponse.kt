package com.lazyxu.network.respose

import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.network.entity.base.BaseResponse
import com.lazyxu.network.error.ApiException
import com.lazyxu.network.respose.ApiResponse.Error
import com.lazyxu.network.respose.ApiResponse.Success


/**
 * 密封类:是一种同时拥有枚举类 enum 和 普通类 class 特性的类
 * 注意，密封类及其子类必须声明在同一个 kotlin 文件中
 *
 * 使用密封类的话，when 表达式可以覆盖所有情况，不需要再添加 else 语句
 * 反编译可以看到，[ApiResponse] 类其实是一个抽象类，[Success] 和 [Error] 继承了这个抽象类
 *
 * 每一个枚举常量值存在一个实例
 * 密封类的一个子类可以有多个状态的实例（eg：获取接口数据失败，可以分为接口请求失败、数据解析失败等）
 *
 * [ApiResponse] 类中的处理也可以写一个适配器放在 okhttp的.addConverterFactory 中
 */

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: ApiException) : ApiResponse<Nothing>()
    class Empty<T>() : ApiResponse<T>()
    data class Fail<T>(val errorCode: Int?, val errorMsg: String?) : ApiResponse<T>()

}

fun <T> BaseResponse<T>.handleResponse(): ApiResponse<T> {
    return if (this.isSuccess) {
        val data = this.data
        if (data == null || data is List<*> && (data as List<*>).isEmpty()) {
            LogUtils.d(LogTag.THREAD, "${Thread.currentThread().name}:Empty")
            ApiResponse.Empty()
        } else {
            LogUtils.d(LogTag.THREAD, "${Thread.currentThread().name}:Success")
            Success(data)
        }
    } else {
        LogUtils.d(LogTag.THREAD, "${Thread.currentThread().name}:Fail")
        ApiResponse.Fail(this.errorCode, this.errorMsg)
    }
}