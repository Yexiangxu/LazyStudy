package lazyxu.design.proxy.static

import lazyxu.design.proxy.*




/**
 * User:Lazy_xu
 * Date:2024/01/23
 * Description:
 * FIXME
 */
object ProxyFactory {
    fun create(): ILawSuit {
        return ProxyLawyer(CuiHuaNiu())
    }
}