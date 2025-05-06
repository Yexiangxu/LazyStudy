package com.lazyxu.base.utils.device

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * 隐私相关
 * mac地址(我们项目没用到)
 *
 */
object Device {
    private fun getSimStateBySlotIdx(context: Context, slotIdx: Int): Boolean {
        var simState = TelephonyManager.SIM_STATE_UNKNOWN
        val telephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            simState = telephony.getSimState(slotIdx)
        } else {
            val getSimState = telephony.getSimState(slotIdx)
            if (getSimState != null) {
                try {
                    simState = getSimState.toString().toInt()
                } catch (ignored: Exception) {
                }
            }
        }
        return simState != TelephonyManager.SIM_STATE_UNKNOWN
    }


    fun getDualSimState(context: Context): Boolean {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val phoneCount = telephonyManager.phoneCount // 获取设备支持的 SIM 卡数
        for (slotIdx in 0 until phoneCount) {
            val simState = telephonyManager.getSimState(slotIdx)
            if (simState == TelephonyManager.SIM_STATE_UNKNOWN) {
                return false // 如果某个 SIM 卡状态为未知，则返回 false
            }
        }
        return true // 所有插槽的 SIM 卡都已插入
    }


    fun getImei(context: Context): String? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return null // Android 10+ 直接返回 null
        }
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                telephonyManager.imei ?: telephonyManager.getImei(0) // 双卡设备
            } else {
                telephonyManager.deviceId // Android 7.1 及以下
            }
        } else {
            null // 无权限
        }
    }


    /**
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     */
    fun getDeviceIMEI(context: Context): String? {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        // 检查权限
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return null // 无权限
        }

        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Android 8.0+ 推荐使用 getImei(slotIndex)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // Android 10+ 普通应用无法获取 IMEI，返回 null
                    null
                } else {
                    // Android 8.0~9.0，支持双卡 IMEI
                    telephonyManager.imei ?: telephonyManager.getImei(0) // 默认卡槽 0
                }
            } else {
                // Android 7.1 及以下，使用 deviceId（可能返回 IMEI/MEID）
                telephonyManager.deviceId
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun hasReadPhoneStatePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED
    }
}