package com.lazyxu.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.network.entity.base.BaseResponse
import com.lazyxu.network.error.ExceptionHandler
import com.lazyxu.network.respose.ApiResponse
import com.lazyxu.network.respose.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {
    private val movieIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

    fun <T> launchFlow(
        requestBlock: suspend () -> BaseResponse<T>,
        responseBlock: ((ApiResponse<T>) -> Unit)? = null,
        showLoading: ((Boolean) -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            flow {
                LogUtils.d(LogTag.THREAD, "${Thread.currentThread().name}:launchFlow")
                val response = requestBlock().handleResponse()
                emit(response)
            }.onStart {
                LogUtils.d(LogTag.HTTP, "${Thread.currentThread().name}:onStart")
                showLoading?.invoke(true)
            }.onCompletion {//请求完成，包括成功和失败
                LogUtils.d(LogTag.HTTP, "${Thread.currentThread().name}:onCompletion")
                showLoading?.invoke(false)
            }.catch {//处理异常
                LogUtils.d(LogTag.HTTP, "${Thread.currentThread().name}:catch")
                ApiResponse.Error(ExceptionHandler.handleException(it))
            }.flowOn(Dispatchers.IO).collect {//收集Flow中的数据，并对数据进行处理
                    LogUtils.d(LogTag.HTTP, "${Thread.currentThread().name}:flowOn")
                    responseBlock?.invoke(it)
                }
        }
    }
}