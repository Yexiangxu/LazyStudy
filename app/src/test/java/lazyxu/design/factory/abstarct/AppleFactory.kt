package lazyxu.design.factory.abstarct

import lazyxu.design.factory.Computer
import lazyxu.design.factory.MacComputer




/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class AppleFactory : AbstractFactory {
    override fun makeComputer(): Computer {
        return MacComputer()
    }

    override fun makeMobilePhone(): MobilePhone {
        return IPhone()
    }
}