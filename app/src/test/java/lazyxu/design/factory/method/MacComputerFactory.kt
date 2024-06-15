package lazyxu.design.factory.method

import lazyxu.design.factory.Computer
import lazyxu.design.factory.MacComputer




/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class MacComputerFactory:ComputerFactory {
    override fun makeComputer(): Computer {
        return MacComputer()
    }
}