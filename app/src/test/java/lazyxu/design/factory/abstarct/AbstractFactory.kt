package lazyxu.design.factory.abstarct

import lazyxu.design.factory.Computer

/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
interface  AbstractFactory {
    fun makeComputer(): Computer?
    fun makeMobilePhone(): MobilePhone?
}