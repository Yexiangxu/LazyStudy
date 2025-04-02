package com.lazyxu.base.utils.detection.lahm

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Debug
import android.provider.Settings
import android.text.TextUtils
import com.lazyxu.base.utils.detection.lahm.library.CommandUtil
import com.lazyxu.base.utils.detection.lahm.library.EasyProtectorLib
import com.lazyxu.base.utils.detection.lahm.library.SecurityCheckUtil
import java.io.File

/**
 * 风控工具类
 * 若要将风控工具类封装成sdk，可仅keep DetectionEntry 供外部使用
 * 在 DetectionEntry 中添加初始化方法，在Application中初始化
 */
internal object DetectionUtils {
    /**
     * 是否打开了usb调试模式
     */
    fun isOpenUsb(context: Context): Boolean {
        return Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.ADB_ENABLED, 0
        ) != 0
    }

    fun isOpenVpn(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }

    fun isOpenProxy(): Boolean {
        val proxyHost = System.getProperty("http.proxyHost")
        val proxyPort = System.getProperty("http.proxyPort")?.toIntOrNull() ?: -1
        return !proxyHost.isNullOrEmpty() && proxyPort != -1

    }

    private var emulatorType: String = ""
    fun isEmulatorDevice(context: Context): Boolean {
        if (EasyProtectorLib.checkIsRunningInEmulator(context) {
                emulatorType = it
            }) {
            return true
        }
        val buildProduct = Build.PRODUCT.lowercase()
        val buildManufacturer = Build.MANUFACTURER.lowercase()
        val buildHardware = Build.HARDWARE.lowercase()
        val buildModel = Build.MODEL.lowercase()
        val buildDevice = Build.DEVICE.lowercase()
        val buildBrand = Build.BRAND.lowercase()
        val buildFingerprint = Build.FINGERPRINT

        //虚拟机
        val DALVIK_VM_ISA_ARM = getProperty("ro.dalvik.vm.isa.arm")
        val DALVIK_VM_ISA_ARM64 = getProperty("ro.dalvik.vm.isa.arm64")
        if (buildProduct.contains("sdk") ||
            buildProduct.contains("andy") ||
            buildProduct.contains("ttvm_hdragon") ||
            buildProduct.contains("google_sdk") ||
            buildProduct.contains("droid4x") ||
            buildProduct.contains("nox") ||
            buildProduct.contains("sdk_x86") ||
            buildProduct.contains("sdk_google") ||
            buildProduct.contains("vbox86p")
        ) {
            emulatorType = "2-${buildProduct}"
            return true
        }
        if (buildManufacturer == "unknown" ||
            buildManufacturer == "genymotion" ||
            buildManufacturer.contains("andy") ||
            buildManufacturer.contains("mit") ||
            buildManufacturer.contains("nox") ||
            buildManufacturer.contains("tiantianvm") ||
            buildManufacturer.contains("netease")  //mumu  by 锐哥 2022.7.14
        ) {
            emulatorType = "3-${buildManufacturer}"
            return true
        }
        if (buildBrand.contains("generic") ||
            buildBrand.contains("generic_x86") ||
            buildBrand.contains("ttvm") ||
            buildBrand.contains("andy")
        ) {
            emulatorType = "4-${buildBrand}"
            return true
        }
        if (buildDevice.contains("generic") ||
            buildDevice.contains("generic_x86") ||
            buildDevice.contains("andy") ||
            buildDevice.contains("ttvm_hdragon") ||
            buildDevice.contains("droid4x") ||
            buildDevice.contains("nox") ||
            buildDevice.contains("generic_x86_64") ||
            buildDevice.contains("vbox86p")
        ) {
            emulatorType = "5-${buildDevice}"
            return true
        }

        if (buildModel.contains("sdk") ||
            buildModel.contains("google_sdk") ||
            buildModel.contains("emulator") ||
            buildModel.contains("mumu") ||
            buildModel.contains("virtual") ||
            buildModel.contains("droid4x") ||
            buildModel.contains("tiantianvm") ||
            buildModel.contains("andy") ||
            buildModel.contains("android sdk built for x86_64") ||
            buildModel.contains("android sdk built for x86")
        ) {
            emulatorType = "6-${buildModel}"
            return true
        }
        if (buildHardware.contains("goldfish", ignoreCase = true) ||
            buildHardware.contains("ranchu") ||
            buildHardware.contains("vbox86") ||
            buildHardware.contains("nox") ||
            buildHardware.contains("ttvm_x86")
        ) {
            emulatorType = "7-${buildHardware}"
            return true
        }
        val fn = emulatorFiles.find { File(it).exists() }
        if (fn != null) {
            emulatorType = "8-${fn}"
            return true
        }
        if (buildFingerprint.contains("andy") ||
            buildFingerprint.contains("ttvm_hdragon") ||
            buildFingerprint.startsWith("generic") ||
            buildFingerprint.contains("vbox") ||
            buildFingerprint.contains("test-keys")
        ) {
            emulatorType = "9-${buildFingerprint}"
            return true
        }
        if(DALVIK_VM_ISA_ARM.contains("x86") || DALVIK_VM_ISA_ARM64.contains("x86")){
            emulatorType = "10-${DALVIK_VM_ISA_ARM}|${DALVIK_VM_ISA_ARM64}"
            return true
        }
        return false

    }

    /**
     * 检测多开应用（VirtualApp 类工具）
     * 某些刷量工具会使用多开环境（如双开助手、平行空间），可以通过检测 VirtualApp 相关的特征文件判断
     */
    fun isInVirtualEnvironment(context: Context): Boolean {
        val packageName = context.packageName
        val appInfo = context.packageManager.getApplicationInfo(packageName, 0)
        return appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0 &&
                packageName.contains(":")
    }

    /**
     *检测系统 Hook（Xposed、Frida 等）
     * Hook 工具通常会在 /system/lib/ 目录或环境变量中留下痕迹
     */
    fun isXposedInstalled(): Boolean {
        return try {
            val classLoader = ClassLoader.getSystemClassLoader()
            classLoader.loadClass("de.robv.android.xposed.XposedBridge") != null
        } catch (e: ClassNotFoundException) {
            false
        }
    }

    /**
     * 检测 root（获取超级用户权限的设备）
     * 刷机后的设备更容易用于批量操作，可以通过检查 su 二进制文件判断是否 root
     */
    fun isDeviceRooted(): Boolean {
        return SecurityCheckUtil.getSingleInstance().isRoot()
    }


    /**
     * 开发者模式有没有打开
     */
    fun isOpenDev(context: Context): Boolean {
        if (checkIsDebugVersion(context) ||
            checkIsDebuggerConnected()
        ) {
            return true
        }
        return Settings.Secure.getInt(
            context.contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
        ) != 0
    }


    /**
     * 检测app是否为debug版本
     */
    private fun checkIsDebugVersion(context: Context): Boolean {
        return (context.applicationInfo.flags
                and ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }

    /**
     * 检测是否连上调试器
     */
    private fun checkIsDebuggerConnected(): Boolean {
        return Debug.isDebuggerConnected()
    }

    private val emulatorFiles = arrayOf( // vbox模拟器文件
        "/data/youwave_id",
        "/dev/vboxguest",
        "/dev/vboxuser",
        "/mnt/prebundledapps/bluestacks.prop.orig",
        "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.note",
        "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s2",
        "/mnt/prebundledapps/propfiles/ics.bluestacks.prop.s3",
        "/mnt/sdcard/bstfolder/InputMapper/com.bluestacks.appmart.cfg",
        "/mnt/sdcard/buildroid-gapps-ics-20120317-signed.tgz",
        "/mnt/sdcard/windows/InputMapper/com.bluestacks.appmart.cfg",
        "/proc/irq/9/vboxguest",
        "/sys/bus/pci/drivers/vboxguest",
        "/sys/bus/pci/drivers/vboxguest/0000:00:04.0",
        "/sys/bus/pci/drivers/vboxguest/bind",
        "/sys/bus/pci/drivers/vboxguest/module",
        "/sys/bus/pci/drivers/vboxguest/new_id",
        "/sys/bus/pci/drivers/vboxguest/remove_id",
        "/sys/bus/pci/drivers/vboxguest/uevent",
        "/sys/bus/pci/drivers/vboxguest/unbind",
        "/sys/bus/platform/drivers/qemu_pipe",
        "/sys/bus/platform/drivers/qemu_trace",
        "/sys/class/bdi/vboxsf-c",
        "/sys/class/misc/vboxguest",
        "/sys/class/misc/vboxuser",
        "/sys/devices/virtual/bdi/vboxsf-c",
        "/sys/devices/virtual/misc/vboxguest",
        "/sys/devices/virtual/misc/vboxguest/dev",
        "/sys/devices/virtual/misc/vboxguest/power",
        "/sys/devices/virtual/misc/vboxguest/subsystem",
        "/sys/devices/virtual/misc/vboxguest/uevent",
        "/sys/devices/virtual/misc/vboxuser",
        "/sys/devices/virtual/misc/vboxuser/dev",
        "/sys/devices/virtual/misc/vboxuser/power",
        "/sys/devices/virtual/misc/vboxuser/subsystem",
        "/sys/devices/virtual/misc/vboxuser/uevent",
        "/sys/module/vboxguest",
        "/sys/module/vboxguest/coresize",
        "/sys/module/vboxguest/drivers",
        "/sys/module/vboxguest/drivers/pci:vboxguest",
        "/sys/module/vboxguest/holders",
        "/sys/module/vboxguest/holders/vboxsf",
        "/sys/module/vboxguest/initsize",
        "/sys/module/vboxguest/initstate",
        "/sys/module/vboxguest/notes",
        "/sys/module/vboxguest/notes/.note.gnu.build-id",
        "/sys/module/vboxguest/parameters",
        "/sys/module/vboxguest/parameters/log",
        "/sys/module/vboxguest/parameters/log_dest",
        "/sys/module/vboxguest/parameters/log_flags",
        "/sys/module/vboxguest/refcnt",
        "/sys/module/vboxguest/sections",
        "/sys/module/vboxguest/sections/.altinstructions",
        "/sys/module/vboxguest/sections/.altinstr_replacement",
        "/sys/module/vboxguest/sections/.bss",
        "/sys/module/vboxguest/sections/.data",
        "/sys/module/vboxguest/sections/.devinit.data",
        "/sys/module/vboxguest/sections/.exit.text",
        "/sys/module/vboxguest/sections/.fixup",
        "/sys/module/vboxguest/sections/.gnu.linkonce.this_module",
        "/sys/module/vboxguest/sections/.init.text",
        "/sys/module/vboxguest/sections/.note.gnu.build-id",
        "/sys/module/vboxguest/sections/.rodata",
        "/sys/module/vboxguest/sections/.rodata.str1.1",
        "/sys/module/vboxguest/sections/.smp_locks",
        "/sys/module/vboxguest/sections/.strtab",
        "/sys/module/vboxguest/sections/.symtab",
        "/sys/module/vboxguest/sections/.text",
        "/sys/module/vboxguest/sections/__ex_table",
        "/sys/module/vboxguest/sections/__ksymtab",
        "/sys/module/vboxguest/sections/__ksymtab_strings",
        "/sys/module/vboxguest/sections/__param",
        "/sys/module/vboxguest/srcversion",
        "/sys/module/vboxguest/taint",
        "/sys/module/vboxguest/uevent",
        "/sys/module/vboxguest/version",
        "/sys/module/vboxsf",
        "/sys/module/vboxsf/coresize",
        "/sys/module/vboxsf/holders",
        "/sys/module/vboxsf/initsize",
        "/sys/module/vboxsf/initstate",
        "/sys/module/vboxsf/notes",
        "/sys/module/vboxsf/notes/.note.gnu.build-id",
        "/sys/module/vboxsf/refcnt",
        "/sys/module/vboxsf/sections",
        "/sys/module/vboxsf/sections/.bss",
        "/sys/module/vboxsf/sections/.data",
        "/sys/module/vboxsf/sections/.exit.text",
        "/sys/module/vboxsf/sections/.gnu.linkonce.this_module",
        "/sys/module/vboxsf/sections/.init.text",
        "/sys/module/vboxsf/sections/.note.gnu.build-id",
        "/sys/module/vboxsf/sections/.rodata",
        "/sys/module/vboxsf/sections/.rodata.str1.1",
        "/sys/module/vboxsf/sections/.smp_locks",
        "/sys/module/vboxsf/sections/.strtab",
        "/sys/module/vboxsf/sections/.symtab",
        "/sys/module/vboxsf/sections/.text",
        "/sys/module/vboxsf/sections/__bug_table",
        "/sys/module/vboxsf/sections/__param",
        "/sys/module/vboxsf/srcversion",
        "/sys/module/vboxsf/taint",
        "/sys/module/vboxsf/uevent",
        "/sys/module/vboxsf/version",
        "/sys/module/vboxvideo",
        "/sys/module/vboxvideo/coresize",
        "/sys/module/vboxvideo/holders",
        "/sys/module/vboxvideo/initsize",
        "/sys/module/vboxvideo/initstate",
        "/sys/module/vboxvideo/notes",
        "/sys/module/vboxvideo/notes/.note.gnu.build-id",
        "/sys/module/vboxvideo/refcnt",
        "/sys/module/vboxvideo/sections",
        "/sys/module/vboxvideo/sections/.data",
        "/sys/module/vboxvideo/sections/.exit.text",
        "/sys/module/vboxvideo/sections/.gnu.linkonce.this_module",
        "/sys/module/vboxvideo/sections/.init.text",
        "/sys/module/vboxvideo/sections/.note.gnu.build-id",
        "/sys/module/vboxvideo/sections/.rodata.str1.1",
        "/sys/module/vboxvideo/sections/.strtab",
        "/sys/module/vboxvideo/sections/.symtab",
        "/sys/module/vboxvideo/sections/.text",
        "/sys/module/vboxvideo/srcversion",
        "/sys/module/vboxvideo/taint",
        "/sys/module/vboxvideo/uevent",
        "/sys/module/vboxvideo/version",
        "/system/app/bluestacksHome.apk",
        "/system/bin/androVM-prop",
        "/system/bin/androVM-vbox-sf",
        "/system/bin/androVM_setprop",
        "/system/bin/get_androVM_host",
        "/system/bin/mount.vboxsf",
        "/system/etc/init.androVM.sh",
        "/system/etc/init.buildroid.sh",
        "/system/lib/hw/audio.primary.vbox86.so",
        "/system/lib/hw/camera.vbox86.so",
        "/system/lib/hw/gps.vbox86.so",
        "/system/lib/hw/gralloc.vbox86.so",
        "/system/lib/hw/sensors.vbox86.so",
        "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest",
        "/system/lib/modules/3.0.8-android-x86+/extra/vboxguest/vboxguest.ko",
        "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf",
        "/system/lib/modules/3.0.8-android-x86+/extra/vboxsf/vboxsf.ko",
        "/system/lib/vboxguest.ko",
        "/system/lib/vboxsf.ko",
        "/system/lib/vboxvideo.ko",
        "/system/usr/idc/androVM_Virtual_Input.idc",
        "/system/usr/keylayout/androVM_Virtual_Input.kl",
        "/system/xbin/mount.vboxsf",
        "/ueventd.android_x86.rc",
        "/ueventd.vbox86.rc",
        "/ueventd.goldfish.rc",
        "/fstab.vbox86",
        "/init.vbox86.rc",
        "/init.goldfish.rc",
        // ========针对原生Android模拟器 内核：goldfish===========
        "/sys/module/goldfish_audio",
        "/sys/module/goldfish_sync",
        // ========针对蓝叠模拟器===========
        "/data/app/com.bluestacks.appmart-1.apk",
        "/data/app/com.bluestacks.BstCommandProcessor-1.apk",
        "/data/app/com.bluestacks.help-1.apk",
        "/data/app/com.bluestacks.home-1.apk",
        "/data/app/com.bluestacks.s2p-1.apk",
        "/data/app/com.bluestacks.searchapp-1.apk",
        "/data/bluestacks.prop",
        "/data/data/com.androVM.vmconfig",
        "/data/data/com.bluestacks.accelerometerui",
        "/data/data/com.bluestacks.appfinder",
        "/data/data/com.bluestacks.appmart",
        "/data/data/com.bluestacks.appsettings",
        "/data/data/com.bluestacks.BstCommandProcessor",
        "/data/data/com.bluestacks.bstfolder",
        "/data/data/com.bluestacks.help",
        "/data/data/com.bluestacks.home",
        "/data/data/com.bluestacks.s2p",
        "/data/data/com.bluestacks.searchapp",
        "/data/data/com.bluestacks.settings",
        "/data/data/com.bluestacks.setup",
        "/data/data/com.bluestacks.spotlight",
        // ========针对逍遥安卓模拟器===========
// 虚拟化网卡和pci，可能存在误判，不可靠
//            "/sys/module/virtio_net",
//            "/sys/module/virtio_pci",
        "/data/data/com.microvirt.download",
        "/data/data/com.microvirt.guide",
        "/data/data/com.microvirt.installer",
        "/data/data/com.microvirt.launcher",
        "/data/data/com.microvirt.market",
        "/data/data/com.microvirt.memuime",
        "/data/data/com.microvirt.tools",
        // ========针对Mumu模拟器===========
        "/data/data/com.mumu.launcher",
        "/data/data/com.mumu.store",
        "/data/data/com.netease.mumu.cloner"
    )

    private fun getProperty(propName: String): String {
        val property = CommandUtil.getSingleInstance().getProperty(propName)
        return if (TextUtils.isEmpty(property)) "" else property
    }
}