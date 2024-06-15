package lazyxu.design.factory.method

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
        //生产Mac电脑
        val macFactory: ComputerFactory = MacComputerFactory()
        macFactory.makeComputer().setOperationSystem()

        //生产小米电脑
        val miFactory: ComputerFactory = MiComputerFactory()
        miFactory.makeComputer().setOperationSystem()
    }

}

