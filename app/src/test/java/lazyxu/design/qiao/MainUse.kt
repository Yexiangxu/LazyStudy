package lazyxu.design.qiao

import org.junit.Test

/**
 * User:Lazy_xu
 * Date:2023/12/05
 * Description:
 * FIXME
 */
class MainUse {
    @Test
    fun main() {
        val red: Color = Red()
        val blue: Color = Blue()
        val circleRed: Shape = Circle(red)
        val circleBlue: Shape = Circle(blue)
        val squareRed: Shape = Square(red)
        val squareBlue: Shape = Square(blue)
        circleRed.draw()
        circleBlue.draw()
        squareRed.draw()
        squareBlue.draw()
    }
}