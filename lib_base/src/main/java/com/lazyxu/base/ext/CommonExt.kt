package com.lazyxu.base.ext

fun Int?.orDef(default: Int = 0) = this ?: default
fun Boolean?.orDef(default: Boolean = false) = this ?: default
fun Float?.orDef(default: Float = 0f) = this ?: default
fun Long?.orDef(default: Long = 0) = this ?: default