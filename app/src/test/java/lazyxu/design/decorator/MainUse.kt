package lazyxu.design.decorator

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
        var animal: Animal = Dog()
        animal = AnimalProtectionEquipment(animal)
        animal.bite()
        animal = AnimalAcceleratorEquipment(animal)
        animal.move()
    }
}
