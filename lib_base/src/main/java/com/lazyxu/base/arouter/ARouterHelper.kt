package com.lazyxu.base.arouter

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.lazyxu.base.R
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.utils.AppToast
import com.lazyxu.base.utils.NetUtils
import java.io.Serializable

object ARouterHelper {
    sealed class ParamWrapper {
        data class StringParam(val value: String) : ParamWrapper()
        data class IntParam(val value: Int) : ParamWrapper()
        data class LongParam(val value: Long) : ParamWrapper()
        data class DoubleParam(val value: Double) : ParamWrapper()
        data class ShortParam(val value: Short) : ParamWrapper()
        data class BundleParam(val value: Bundle) : ParamWrapper()
        data class FloatParam(val value: Float) : ParamWrapper()
        data class BooleanParam(val value: Boolean) : ParamWrapper()
        data class SerializableParam(val value: Serializable) : ParamWrapper()
        data class ParcelableParam(val value: Parcelable) : ParamWrapper()
        data class StringListParam(val value: ArrayList<String>) : ParamWrapper()
        data class ParcelableListParam(val value: ArrayList<Parcelable>) : ParamWrapper()
    }

    private fun Postcard.getParams(params: Map<String, Any> = mapOf()) {
        if (params.isEmpty()) return
        params.forEach { entry ->
            when (val param = entry.value) {
                is ParamWrapper.StringParam -> withString(entry.key, param.value)
                is ParamWrapper.IntParam -> withInt(entry.key, param.value)
                is ParamWrapper.LongParam -> withLong(entry.key, param.value)
                is ParamWrapper.DoubleParam -> withDouble(entry.key, param.value)
                is ParamWrapper.ShortParam -> withShort(entry.key, param.value)
                is ParamWrapper.BundleParam -> withBundle(entry.key, param.value)
                is ParamWrapper.FloatParam -> withFloat(entry.key, param.value)
                is ParamWrapper.BooleanParam -> withBoolean(entry.key, param.value)
                is ParamWrapper.SerializableParam -> withSerializable(
                    entry.key,
                    param.value
                )

                is ParamWrapper.ParcelableParam -> withParcelable(entry.key, param.value)
                is ParamWrapper.StringListParam -> withStringArrayList(
                    entry.key,
                    param.value
                )

                is ParamWrapper.ParcelableListParam -> withParcelableArrayList(
                    entry.key,
                    param.value
                )
            }
        }
    }

    fun goActivity(path: String, params: Map<String, Any> = mapOf()) {
        val postcard = ARouter.getInstance().build(path)
        postcard.getParams(params)
        postcard.navigation()
    }

    /**
     * 刚进入页面就需要网络请求，优先判断是否链接网络优化用户体验
     */
    fun goActivityNeedNet(path: String, params: Map<String, Any> = mapOf()) {
        if (NetUtils.isConnected(BaseApplication.INSTANCE)) {
            goActivity(path, params)
        } else {
            AppToast.show(R.string.net_disconnect)
        }
    }

    fun Activity.goActivityFinishCurrent(path: String) {
        ARouter.getInstance().build(path).navigation()
        this.finish()
    }
}