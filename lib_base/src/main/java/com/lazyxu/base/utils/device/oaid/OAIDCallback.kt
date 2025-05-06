package com.lazyxu.base.utils.device.oaid

interface OAIDCallback {
    /**
     * OAID改变了
     */
    fun onOAIDChanged(oaid: String?)
}