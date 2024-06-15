package lazyxu.design.decorator

/**
 * User:Lazy_xu
 * Date:2024/01/31
 * Description:
 * FIXME
 */
abstract class AnimalEquipment(private val animal: Animal) : Animal {
    override fun move() {
        beforeMove()
        animal.move()
        afterMove()
    }
    override fun bite() {
        beforeBite()
        animal.bite()
        afterBite()
    }
    protected abstract fun beforeMove()
    protected abstract fun afterMove()
    protected abstract fun beforeBite()
    protected abstract fun afterBite()
}