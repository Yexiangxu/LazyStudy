package com.lazyxu.base.utils.detection.lahm.library

import android.content.Context
import java.net.UnknownHostException

/**
 * Project Name:EasyProtector
 * Package Name:com.lahm.library
 * Created by lahm on 2018/5/14 下午9:38 .
 */
object EasyProtectorLib {
    fun checkSignature(context: Context?): String {
        return SecurityCheckUtil.getSingleInstance().getSignature(context)
    }

    fun checkIsDebug(context: Context?): Boolean {
        return SecurityCheckUtil.getSingleInstance().checkIsDebugVersion(context) ||
                SecurityCheckUtil.getSingleInstance().checkIsDebuggerConnected()
    }

    fun checkIsPortUsing(host: String?, port: Int): Boolean {
        try {
            return SecurityCheckUtil.getSingleInstance().isPortUsing(host, port)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            return true
        }
    }

    fun checkIsRoot(): Boolean {
        return SecurityCheckUtil.getSingleInstance().isRoot
    }

    fun checkIsXposedExist(): Boolean {
        return SecurityCheckUtil.getSingleInstance().isXposedExistByThrow
    }

    fun checkXposedExistAndDisableIt(): Boolean {
        return SecurityCheckUtil.getSingleInstance().tryShutdownXposed()
    }

    fun checkHasLoadSO(soName: String?): Boolean {
        return SecurityCheckUtil.getSingleInstance().hasReadProcMaps(soName)
    }

    fun checkIsBeingTracedByJava(): Boolean {
        return SecurityCheckUtil.getSingleInstance().readProcStatus()
    }

    fun checkIsBeingTracedByC() {
        NDKUtil.loadLibrariesOnce(null)
        //        NDKUtil.loadLibraryByName("antitrace");
    }

    fun checkIsRunningInEmulator(
        context: Context?,
        callback: EmulatorCheckCallback? = null
    ): Boolean {
        return EmulatorCheckUtil.readSysProperty(context, callback)
    }

    fun checkIsRunningInVirtualApk(uniqueMsg: String?, callback: VirtualCheckCallback?): Boolean {
        return VirtualApkCheckUtil.getSingleInstance()
            .checkByCreateLocalServerSocket(uniqueMsg, callback)
    }
}
