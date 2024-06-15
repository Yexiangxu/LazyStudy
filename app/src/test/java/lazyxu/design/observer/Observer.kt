package lazyxu.design.observer

/**
 * User:Lazy_xu
 * Date:2023/12/06
 * Description:
 * FIXME
 */
class Observer : IObserver {
    override fun update() {
        println("更新了")
    }
}