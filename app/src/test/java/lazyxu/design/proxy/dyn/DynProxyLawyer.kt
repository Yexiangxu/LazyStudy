package lazyxu.design.proxy.dyn

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * User:Lazy_xu
 * Date:2024/01/19
 * Description:
 * FIXME
 */
class DynProxyLawyer( //被代理的对象
    private val target: Any
) : InvocationHandler {
    @Throws(Throwable::class)
    override fun invoke(
        proxy: Any?,
        method: Method?,
        args: Array<out Any>?
    ): Any? {
        println("案件进展：" + method?.name)
        return method?.invoke(target, *(args ?: emptyArray()))
    }
}