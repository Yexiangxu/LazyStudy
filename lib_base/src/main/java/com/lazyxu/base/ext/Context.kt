package com.lazyxu.base.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.lazyxu.base.base.BaseApplication

val Activity.isDestroy: Boolean
    get() = this.isFinishing || this.isDestroyed


val Context.validContext: Context
    get() {
        var isValid = true
        if (this is Activity) {
            isValid = !isDestroy
        }
        if (this is ContextWrapper) {
            val baseContext = this.baseContext
            if (baseContext is Activity) {
                isValid = !baseContext.isDestroy
            }
        }
        return if (isValid) this else BaseApplication.INSTANCE
    }






