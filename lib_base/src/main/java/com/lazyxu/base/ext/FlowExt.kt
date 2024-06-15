package com.lazyxu.base.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * User:Lazy_xu
 * Date:2024/05/15
 * Description:
 * FIXME
 */
/**
 * 倒计时
 */
fun countDownCoroutines(
    total: Int,
    scope: CoroutineScope,
    onTick: (Int) -> Unit,
    onStart: (() -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
): Job {
    return flow {
        for (i in total downTo 1) {
            emit(i)
            delay(1000)
        }
    }
        .flowOn(Dispatchers.Main)//用于切换上游的上下文,也就是会影响flow{}构建器函数和之前的中间操作符的执行上下文
        .onStart { onStart?.invoke() }//在流开始收集之前调用，可以用于执行一些初始化操作，例如打开文件或数据库连接等
        .onEach { onTick.invoke(it) }//在每个元素被发射之后调用,可以用于执行一些通用操作，例如日志记录或更新UI等
        .onCompletion { onFinish?.invoke() }//在流完成收集之后调用，无论是正常完成还是异常终止。可以用于执行一些清理操作，例如关闭文件或数据库连接等。onCompletion可以接收一个可空的Throwable参数，表示流终止的原因，如果为null，则表示正常完成
        .launchIn(scope)//用于切换下游的上下文,也就是影响collect操作符和之后的中间操作符的执行上下文
}