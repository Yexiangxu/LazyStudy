package lazyxu.design.adapter.objects

import lazyxu.design.adapter.AdapteeImp
import lazyxu.design.adapter.objects.Log2Adapter
import org.junit.Test

/**
 * User:Lazy_xu
 * Date:2023/12/05
 * Description:
 * FIXME
 */
class MainUse {
    @Test
    fun main() {
        val log= Log2Adapter(AdapteeImp())
        log.debug("tag","message")
    }
}

