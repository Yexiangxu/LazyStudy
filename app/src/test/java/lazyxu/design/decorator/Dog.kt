package lazyxu.design.decorator

/**
 * User:Lazy_xu
 * Date:2024/01/31
 * Description:
 * FIXME
 */
class Dog : Animal {
    override fun move() {
        println("狗子跑起来吧。。。。。")
    }
    override fun bite() {
        println("狗子生气了，咬你。。。。。")
    }
}