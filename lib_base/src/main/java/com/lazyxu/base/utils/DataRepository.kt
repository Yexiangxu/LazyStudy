package com.lazyxu.base.utils


object DataRepository : MMKVOwner {
    var isFirstLaunch by mmkvBool(default = true)
    fun test() {
        isFirstLaunch = true
    }

}