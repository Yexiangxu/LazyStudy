package com.lazyxu.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * 用于判断是不是联网状态
 */
object NetUtils {
    fun isNetworkConnected(context: Context?): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT < 23) {
            val mWiFiNetworkInfo = cm.activeNetworkInfo
            if (mWiFiNetworkInfo != null) {
                //WIFI
                if (mWiFiNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    return true
                    //移动数据
                } else if (mWiFiNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    return true
                }
            }
        } else {
            val network = cm.activeNetwork
            if (network != null) {
                val nc = cm.getNetworkCapabilities(network)
                if (nc != null) {
                    //WIFI
                    if (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                        //移动数据
                    } else if (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}