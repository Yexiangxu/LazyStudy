package lazyxu.design.factory.abstarct

import org.junit.Test


/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */

class MainUse {

    @Test
    fun main(){
        //使用苹果工厂生产苹果公司的系列产品
        val appleFactory: AbstractFactory = AppleFactory()
        appleFactory.makeComputer()?.setOperationSystem()
        appleFactory.makeMobilePhone()?.setOperationSystem()
        //使用小米工厂生产小米公司的系列产品
        val miFactory: AbstractFactory = XiaoMiFactory()
        miFactory.makeComputer()?.setOperationSystem()
        miFactory.makeMobilePhone()?.setOperationSystem()
    }

}

