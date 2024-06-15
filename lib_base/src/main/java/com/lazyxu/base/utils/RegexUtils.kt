package com.lazyxu.base.utils

import java.util.regex.Pattern


/**
 * 校验工具类(正则表达式使用)
 * https://www.runoob.com/regexp/regexp-tutorial.html
 */

object RegexUtils {
    private val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }


    /**
     * 判断手机号码格式校验
     */
    private val p = Pattern.compile("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")
    fun isValidMobile(phone: String): Boolean {
        val m = p.matcher(phone)
        return  m.matches()//phone.length == 11 &&
    }
}
