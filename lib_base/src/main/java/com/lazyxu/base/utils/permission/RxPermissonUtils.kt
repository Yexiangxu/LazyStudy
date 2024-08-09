package com.lazyxu.base.utils.permission

import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION

object RxPermissonUtils {
    const val TAG: String = "RxPermissionTag"

    fun isMarshmallow(): Boolean {
        return VERSION.SDK_INT >= 23
    }


}