package com.lazyxu.base.utils.detection.lahm

/**
 * 风控回调
 */
interface IRCallback {
    fun canAcceptUserPrivacy(): Boolean //是否能访问隐私数据。用于风控归因模块内部对隐私的访问控制
}