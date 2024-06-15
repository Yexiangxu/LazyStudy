package lazyxu.design.factory.simple

import lazyxu.design.factory.Computer
import lazyxu.design.factory.MacComputer
import lazyxu.design.factory.MiComputer

/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class SimpleComputerFactory {
    fun makeComputer(brand:String): Computer?{
        var computer: Computer? = null
        when (brand) {
            "mac" -> computer = MacComputer()
            "mi" -> computer = MiComputer()
            else -> {}
        }
        return computer
    }
}