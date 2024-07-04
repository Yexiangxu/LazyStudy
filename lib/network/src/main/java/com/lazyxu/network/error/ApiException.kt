package com.lazyxu.network.error

import java.io.IOException

/**
 * User:Lazy_xu
 * Date:2024/06/17
 * Description:
 * FIXME
 */
/**
 * 结果异常类
 * 服务器非200状态，对应的异常
 */
open class ApiException : Exception {
    var errCode: Int
    var errMsg: String

    constructor(error: StatusCode, e: Throwable? = null) : super(e) {
        errCode = error.code
        errMsg = error.errorMsg
    }

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.errCode = code
        this.errMsg = msg
    }
}

/**
 * 无网络连接异常使用 IOException
 */
class NoNetWorkException : IOException {
    var errCode: Int
    var errMsg: String

    constructor(error: StatusCode, e: Throwable? = null) : super(e) {
        errCode = error.code
        errMsg = error.errorMsg
    }
}