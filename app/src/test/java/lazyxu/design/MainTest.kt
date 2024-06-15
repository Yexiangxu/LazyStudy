package lazyxu.design

import org.junit.Test

class MainTest {
    @Test
    fun deSign() {
        val nmae = "王五"
        val handler: Handler = Factory.getHandler(nmae)!!
        handler.AAA(nmae)
    }
}




