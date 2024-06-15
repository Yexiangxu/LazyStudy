package lazyxu.design.adapter.classes

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
        val log= LogAdapter()
        log.debug("tag","message")
    }
}

