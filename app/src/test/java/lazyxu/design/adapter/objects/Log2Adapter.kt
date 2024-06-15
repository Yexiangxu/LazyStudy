package lazyxu.design.adapter.objects

import lazyxu.design.adapter.Adaptee
import lazyxu.design.adapter.Target


/**
 * User:Lazy_xu
 * Date:2024/01/25
 * Description:
 * FIXME
 */
class Log2Adapter(private var adaptee: Adaptee) : Target {
    override fun debug(tag: String?, message: String?) {
        adaptee.d(tag, message)
    }
}