package com.lazyxu.base.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

object HtmlUtils {
    fun fromHtml(html: String): Spanned {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            @Suppress("DEPRECATION")
            return Html.fromHtml(html)
        }
    }
}