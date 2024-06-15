package com.lazyxu.lazystudy.test.singleinstance

/**
 * User:Lazy_xu
 * Date:2023/11/20
 * Description:
 * FIXME
 */
class Singleton4 {

    companion object {
        val singleton4 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton4()
        }
    }


}