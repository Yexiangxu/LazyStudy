package com.lazyxu.base.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.lazyxu.base.R
import com.lazyxu.base.base.BaseApplication

object AppToast {
    private var toast: Toast? = null

    fun show(message: String) {
        MainLooper.instance.runOnUiThread {
            val context = BaseApplication.INSTANCE.applicationContext
            if (toast == null) {
                toast = Toast(context)
            }
            getToastView(context, message)
            toast?.show()
        }
    }

    private fun getToastView(context: Context, message: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.toast_main, null)
        val textView = view.findViewById<TextView>(R.id.tv_toast)
        textView.gravity = Gravity.CENTER
        toast?.view = view
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.duration = Toast.LENGTH_SHORT
        textView.text = message
    }

    fun show(@StringRes message: Int) {
        show(BaseApplication.INSTANCE.applicationContext.getString(message))
    }
}
