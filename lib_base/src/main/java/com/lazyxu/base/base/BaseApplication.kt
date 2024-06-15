package com.lazyxu.base.base

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process
import android.provider.Settings
import android.text.TextUtils
import android.webkit.WebView
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import com.lazyxu.base.BuildConfig
import com.lazyxu.base.R
import com.lazyxu.base.log.LogUtils
import com.lazyxu.base.utils.*
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.tencent.bugly.crashreport.CrashReport


abstract class BaseApplication : Application() {

    companion object {
        lateinit var INSTANCE: Application
//        var refWatcher: RefWatcher? = null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initProject()
        initSdk()
    }

    /**
     * 初始化项目里面需要的
     */
    private fun initProject(){
        AudioPlayManager.instance.init(this)
    }

    /**
     * 初始化第三方sdk
     */
    private fun initSdk() {
        if (BuildConfigs.IS_DEV) {
            Logger.addLogAdapter(AndroidLogAdapter())//用来查看log日志
            Stetho.initializeWithDefaults(this)//用来调试查看数据库
            MyCrashHandler.getInstance().init(this)
//            refWatcher = LeakCanary.install(this)
            DeviceUtil.printDeviceDetailInfo(this)

        }
        if (ProcessUtils.isMainProcess(this)) {
            //Bugly  为了保证运营数据的准确性，建议不要在异步线程初始化Bugly
            val deviceID = resources.getStringArray(R.array.developDeviceId)
            val myId = Settings.System.getString(contentResolver, Settings.Secure.ANDROID_ID)
            if (!TextUtils.isEmpty(myId)) {
                for (mID in deviceID) {
                    if (mID == myId) {
                        CrashReport.setIsDevelopmentDevice(this, true)//设置调试设备（初始化Bugly之前）
                        break
                    }
                }
            }
            val strategy = CrashReport.UserStrategy(this)
            strategy.appVersion = DeviceUtil.VERSION_NAME //strategy.appReportDelay = 10_000
            strategy.appChannel = BuildConfig.CHANNEL_QID
            CrashReport.initCrashReport(
                INSTANCE,
                BuildConfig.BUGLY_APPID,
                BuildConfigs.IS_DEV,
                strategy
            )
            SpUtils.initMMKV(this)
            initArouter()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                WebView.setDataDirectorySuffix(ProcessUtils.getProcessName(Process.myPid()))
            }
        }
        //LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
    }

    private fun initArouter() {
        if (BuildConfigs.IS_DEV) {
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(INSTANCE)
    }


}