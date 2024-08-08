package com.lazyxu.base.arouter

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.lazyxu.base.R
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.utils.AppToast
import com.lazyxu.base.utils.NetUtils
import java.io.Serializable

object ARouterHelper {

    private fun Postcard.getParams(params: Map<String, Any> = mapOf()) {
        if (params.isEmpty()) return
        params.map {
            when (it.value) {
                is String -> withString(it.key, it.value as String)
                is Int -> withInt(it.key, it.value as Int)
                is Long -> withLong(it.key, it.value as Long)
                is Double -> withDouble(it.key, it.value as Double)
                is Short -> withShort(it.key, it.value as Short)
                is Bundle -> withBundle(it.key, it.value as Bundle)
                is Float -> withFloat(it.key, it.value as Float)
                is Boolean -> withBoolean(it.key, it.value as Boolean)
                is Serializable -> withSerializable(it.key, it.value as Serializable)
                is Parcelable -> withParcelable(it.key, it.value as Parcelable)
                is ArrayList<*> -> {
                    when {
                        it.value is ArrayList<*> && (it.value as ArrayList<*>).all { item -> item is String } ->
                            withStringArrayList(it.key, it.value as ArrayList<String>)

                        it.value is ArrayList<*> && (it.value as ArrayList<*>).all { item -> item is Parcelable } ->
                            withParcelableArrayList(it.key, it.value as ArrayList<Parcelable>)

                        else -> {
                        }
                    }
                }

                else -> {

                }
            }
        }
    }

    fun goActivity(path: String, params: Map<String, Any> = mapOf()) {
        val postcard = ARouter.getInstance().build(path)
        postcard.getParams(params)
        postcard.navigation()
    }

    fun getFragment(path: String): Fragment {
        return ARouter.getInstance().build(path).navigation() as Fragment
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