package lazyxu.design.factory.method

import lazyxu.design.factory.Computer
import lazyxu.design.factory.MiComputer




/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class MiComputerFactory :ComputerFactory{
    override fun makeComputer(): Computer {
        return MiComputer()
    }
}