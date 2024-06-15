package lazyxu.design.factory.abstarct

import lazyxu.design.factory.Computer
import lazyxu.design.factory.MiComputer




/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class XiaoMiFactory : AbstractFactory {
    override fun makeComputer(): Computer {
        return MiComputer()
    }

    override fun makeMobilePhone(): MobilePhone {
        return MiPhone()
    }
}