package com.lazyxu.base.utils

object StringUtils {
    fun calculateTime(currenttime: Int): String {
        val time = currenttime / 1000
        val minute: Int
        val second: Int
        return if (time >= 60) {
            minute = time / 60
            second = time % 60
            //分钟在0~9
            if (minute < 10) {
                //判断秒
                if (second < 10) {
                    "0$minute:0$second"
                } else {
                    "0$minute:$second"
                }
            } else {
                //分钟大于10再判断秒
                if (second < 10) {
                    "$minute:0$second"
                } else {
                    "$minute:$second"
                }
            }
        } else {
            second = time
            if (second in 0..9) {
                "00:0$second"
            } else {
                "00:$second"
            }
        }
    }
}