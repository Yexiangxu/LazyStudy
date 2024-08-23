package com.lazyxu.base.utils

import com.lazyxu.base.base.BaseApplication

object ScreenUtils {
    /**
     * 获取屏幕宽度
     *
     * @return
     */
    val screenWidth: Int
        get() = BaseApplication.INSTANCE.resources?.displayMetrics?.widthPixels ?: 0

}