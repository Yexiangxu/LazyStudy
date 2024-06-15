package lazyxu.design.factory.simple

import lazyxu.design.factory.Computer
import lazyxu.design.factory.MacComputer
import lazyxu.design.factory.MiComputer
import org.junit.Test


/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */

class MainUse {
    fun makeComputer(brand:String): Computer?{
        var computer: Computer? = null
        when (brand) {
            "mac" -> computer = MacComputer()
            "mi" -> computer = MiComputer()
            else -> {}
        }
        return computer
    }
    @Test
    fun main(){
        val computer=makeComputer("mac")
        computer?.setOperationSystem()
    }

}

