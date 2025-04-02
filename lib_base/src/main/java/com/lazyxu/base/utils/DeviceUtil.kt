package com.lazyxu.base.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Debug
import android.provider.Settings
import android.util.TypedValue
import com.lazyxu.base.R
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.detection.lahm.DetectionUtils

object DeviceUtil {
    fun printDeviceDetailInfo(context: Context) {
        if (!BuildConfigs.IS_DEV) return

        val displayMetrics = context.resources.displayMetrics
        LogUtils.d("displayMetrics=$displayMetrics")
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryClass = activityManager.memoryClass // 以MB为单位的内存限制
        val largeMemoryClass = activityManager.largeMemoryClass // 使用largeHeap时更高的内存限制


        LogUtils.d("内存=$memoryClass},最大内存=${largeMemoryClass}")
        LogUtils.d("风控====isOpenUsb=${DetectionUtils.isOpenUsb(BaseApplication.INSTANCE)},isOpenVpn=${DetectionUtils.isOpenVpn(BaseApplication.INSTANCE)},isOpenProxy=${DetectionUtils.isOpenProxy()},isEmulatorDevice=${DetectionUtils.isEmulatorDevice(BaseApplication.INSTANCE)},isInVirtualEnvironment=${DetectionUtils.isInVirtualEnvironment(BaseApplication.INSTANCE)},isXposedInstalled=${DetectionUtils.isXposedInstalled()},isDeviceRooted=${DetectionUtils.isDeviceRooted()}，isOpenDev=${DetectionUtils.isOpenDev(BaseApplication.INSTANCE)}")
    }







    /**
     * 是否深色主题
     * 切记 Context 不能用 Application
     */
    fun isDarkTheme(context: Context): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }


    /**
     * 品牌
     */
    val BRAND: String by lazy {
        Build.BRAND
    }

    /**
     * 机型
     */
    val MODLE: String by lazy {
        Build.MODEL
    }

    /**
     * 系统
     */
    val DEVICE_OS: String by lazy {
        Build.VERSION.RELEASE
    }


    /**
     * 获取版本号
     */
    val VERSION_CODE by lazy {
        try {
            val packageInfo: PackageInfo = BaseApplication.INSTANCE.packageManager.getPackageInfo(
                BaseApplication.INSTANCE.packageName,
                0
            )
            val versionCode: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toInt()
            } else {
                packageInfo.versionCode
            }
            versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            -1
        }
    }

    val VERSION_NAME by lazy {
        try {
            val packageInfo: PackageInfo =
                BaseApplication.INSTANCE.packageManager.getPackageInfo(
                    BaseApplication.INSTANCE.packageName,
                    0
                )
            packageInfo.versionName ?: "1.0.0"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            "1.0.0"
        }
    }

//    fun getIMEI(): String? {
//        var imei: String? = ""
//        val old: String = SpUtils.getString(SpKey.CACHED_IMEI)
//        if (Device.canAccessPrivacy() && magicx.device.Device.checkPermission(Manifest.permission.READ_PHONE_STATE) && !magicx.device.Device.HAS_IMEI) { //有读取隐私的能力就读
//            magicx.device.Device.HAS_IMEI = true
//            val telephonyManager =
//                DevicePref.context.getSystemService(Context.TELEPHONY_SERVICE)
//            try {
//                try {
//                    if (Build.VERSION.SDK_INT >= 26) {
//                        imei = telephonyManager.imei
//                    } else {
//                        var deviceId = telephonyManager.deviceId
//                        if (deviceId == null) {
//                            //android.provider.Settings;
//                            deviceId = magicx.device.Device.getAndroidId()
//                        }
//                        imei = deviceId
//                    }
//                } catch (e: SecurityException) {
//                    e.printStackTrace()
//                }
//            } catch (e: Exception) {
//            }
//            imei = magicx.device.Device.returnNotNull(imei)
//            DevicePref.save(DevicePref.CACHED_IMEI, imei)
//        } else {
//            imei = magicx.device.Device.returnNotNull(DevicePref.get(DevicePref.CACHED_IMEI))
//        }
//        if (!TextUtils.isEmpty(imei) && !TextUtils.equals(imei, old)) {
//            magicx.device.Device.infoChangeCallBack(DeviceConstants.IMEI_TYPE, imei)
//        }
//        return imei
//    }

}
