package lazyxu.design.decorator

/**
 * User:Lazy_xu
 * Date:2024/01/31
 * Description:
 * FIXME
 */
class AnimalProtectionEquipment(animal: Animal) : AnimalEquipment(animal) {
    override fun beforeMove() {}
    override fun afterMove() {}
    override fun beforeBite() {
        println("安装合金牙套。。。。。")
    }
    override fun afterBite() {
        println("取下合金牙套。。。。。")
    }
}