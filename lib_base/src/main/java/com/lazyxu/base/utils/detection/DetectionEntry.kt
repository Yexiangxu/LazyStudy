package com.lazyxu.base.utils.detection

import android.content.Context
import com.lazyxu.base.utils.detection.lahm.IRCallback
import java.lang.ref.WeakReference

object DetectionEntry {
    private var mCallback: IRCallback? = null
    private var mContext: WeakReference<Context>? = null

    @JvmStatic
    val canAcceptUserPrivacy: Boolean
        get() {
            return try {
                (mCallback != null) && (mCallback!!.canAcceptUserPrivacy())
            } catch (e: Throwable) {
                false
            }
        }

//    fun isOpenUsb() = DetectionUtils.isOpenUsb()
}