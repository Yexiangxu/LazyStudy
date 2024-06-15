package lazyxu.kotlin

import org.junit.Test
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


/**
 * User:Lazy_xu
 * Date:2024/04/11
 * Description:
 * FIXME
 */
class MainTest {

    @Test
    fun main() {
        exampleFunction("Hello")

    }
    inline fun <reified T> exampleFunction(item: T) {
        if (item is T) {
            println("Item is of type ${T::class.simpleName}")
        } else {
            println("Item is not of type ${T::class.simpleName}")
        }
    }


}

