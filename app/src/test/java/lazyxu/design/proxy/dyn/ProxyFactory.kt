package lazyxu.design.proxy.dyn

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * User:Lazy_xu
 * Date:2024/01/19
 * Description:
 * FIXME
 */
object ProxyFactory {
    fun create(target: Any): Any {
        val handler: InvocationHandler = DynProxyLawyer(target)
        return Proxy.newProxyInstance(target.javaClass.classLoader, target.javaClass.interfaces, handler)
    }
}