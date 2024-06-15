package lazyxu.design.observer

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
        val observer=Observer()
        val observed=Observed()
        observed.register(observer)
        observed.notifyObserver()
    }
}