package com.lazyxu.base.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TimeCountDown {
    private var lastJob: Job? = null
    private val _remainingTime = MutableStateFlow(0)
    val remainingTime: StateFlow<Int> = _remainingTime.asStateFlow()

    fun start(
        total: Int,
        scope: CoroutineScope,
        onStart: (() -> Unit)? = null,
        onTick: ((Int) -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        lastJob?.cancel()
        lastJob = scope.launch(Dispatchers.Main) {
            try {
                onStart?.invoke()
                _remainingTime.value = total
                for (i in total downTo 1) {
                    _remainingTime.value = i
                    onTick?.invoke(i)
                    delay(1000)
                }
                onFinish?.invoke()
            } finally {
                if (_remainingTime.value > 0) {
                    onCancel?.invoke() // 说明倒计时是被取消的
                }
            }
        }
    }

    fun cancel() {
        lastJob?.cancel()
    }
}
