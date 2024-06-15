package com.lazyxu.base.utils

object H5Util {
    /**
     * eg:删除url中的jumpyket6=2
     */
    fun deleteKeyAndValue(loadUrl: String, keyAndValue: String): String {
        var resultUrl = loadUrl
        when {
            loadUrl.contains("&$keyAndValue") -> resultUrl =
                loadUrl.replace("&$keyAndValue".toRegex(), "")
            loadUrl.contains("$keyAndValue&") -> resultUrl =
                loadUrl.replace("${keyAndValue}&".toRegex(), "")
            loadUrl.contains("?$keyAndValue") -> resultUrl =
                loadUrl.replace("\\?$keyAndValue".toRegex(), "")
        }
        return resultUrl
    }
}