package lazyxu.design.proxy.static

import lazyxu.design.proxy.CuiHuaNiu
import org.junit.Test

/**
 * User:Lazy_xu
 * Date:2024/01/23
 * Description:
 * FIXME
 */
class MainUse {
    @Test
    fun main(){
        val proxy=ProxyFactory.create()
        proxy.submit("工资流水在此");
        proxy.defend();
    }
}
