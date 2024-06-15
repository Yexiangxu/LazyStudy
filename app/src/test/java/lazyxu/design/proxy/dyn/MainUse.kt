package lazyxu.design.proxy.dyn

import lazyxu.design.proxy.CuiHuaNiu
import lazyxu.design.proxy.ILawSuit
import org.junit.Test


/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */

class MainUse {

    @Test
    fun main(){
        val proxy = ProxyFactory.create(CuiHuaNiu()) as ILawSuit
        proxy.submit("工资流水在此")
        proxy.defend()
    }

}

