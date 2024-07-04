package com.lazyxu.network.respose

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.lazyxu.base.log.LogTag
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.AppToast
import com.lazyxu.network.error.ExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * User:Lazy_xu
 * Date:2024/06/17
 * Description:
 * FIXME
 */


fun <T> ApiResponse<T>.parseState(listenerBuilder: ResultBuilder<T>.() -> Unit) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    when (this) {
        is ApiResponse.Success -> listener.onSuccess(this.data)
        is ApiResponse.Empty -> listener.onDataEmpty()
        is ApiResponse.Fail -> listener.onFailed(this.errorCode, this.errorMsg)
        is ApiResponse.Error -> listener.onError(this.exception)
    }
}

class ResultBuilder<T> {
    var onSuccess: (data: T) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, errorMsg ->
        errorMsg?.let { AppToast.show(it) }
    }
    var onError: (e: Throwable) -> Unit = { e ->
        e.printStackTrace()
        LogUtils.e(LogTag.HTTP, e.toString())
        val exception = ExceptionHandler.handleException(e)
        AppToast.show(exception.errMsg)
    }
}


fun <T> Flow<ApiResponse<T>>.collectIn(
    lifecycleOwner: LifecycleOwner,
    listenerBuilder: ResultBuilder<T>.() -> Unit,
): Job = lifecycleOwner.lifecycleScope.launch {
    this@collectIn.collect { apiResponse: ApiResponse<T> ->
        apiResponse.parseState(listenerBuilder)
    }
}
