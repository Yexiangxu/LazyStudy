package lazyxu.design.adapter.classes

import lazyxu.design.adapter.AdapteeImp
import lazyxu.design.adapter.Target


/**
 * User:Lazy_xu
 * Date:2024/01/25
 * Description:
 * FIXME
 */
 class LogAdapter : AdapteeImp(), Target {
    override fun debug(tag: String?, message: String?) {
        d(tag,message)
    }
}