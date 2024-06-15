package com.lazyxu.base.utils

import android.Manifest.permission
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresPermission
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.base.constants.SpKey

object VibrateUtils {
    private var vibrator: Vibrator? = null
    @RequiresPermission(permission.VIBRATE)
    fun vibrate(milliseconds: Long=300) {
        if (!SpUtils.getBoolean(SpKey.OPEN_VIBRATE)){
            return
        }
        cancel()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            getVibrator()?.vibrate(
                VibrationEffect.createOneShot(
                    milliseconds,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            getVibrator()?.vibrate(milliseconds)
        }
    }

    @RequiresPermission(permission.VIBRATE)
    private fun cancel() {
        getVibrator()?.cancel()
    }

    private fun getVibrator(): Vibrator? {
        if (vibrator == null) {
            vibrator =
                BaseApplication.INSTANCE.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        return vibrator
    }
}