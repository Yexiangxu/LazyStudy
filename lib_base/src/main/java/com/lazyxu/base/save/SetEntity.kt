package com.lazyxu.base.save

data class SetEntity(val userid: String, val name: String, val age: Int) {
    val isvip = userid == "vip"
    fun isVip() = userid == "vip"
}