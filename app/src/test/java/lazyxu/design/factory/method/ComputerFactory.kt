package lazyxu.design.factory.method

import lazyxu.design.factory.Computer

/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
interface ComputerFactory {
    fun makeComputer():Computer
}