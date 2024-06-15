package lazyxu.design.decorator

/**
 * User:Lazy_xu
 * Date:2024/01/31
 * Description:
 * FIXME
 */
class AnimalAcceleratorEquipment(animal: Animal) : AnimalEquipment(animal) {
    override fun beforeMove() {
        println("打开加速引擎。。。。。")
    }
    override fun afterMove() {
        println("关闭加速引擎。。。。。")
    }
    override fun beforeBite() {}
    override fun afterBite() {}
}