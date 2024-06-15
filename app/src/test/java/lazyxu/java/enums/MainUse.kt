package lazyxu.java.enums

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
        colorType(Color.GREEN)
        colorType2(Color2.GREEN)
        colorType2(Color2.BLUE)
        SingleInstance.SINGLEINSTANCE.getColorName()
    }

    private fun colorType(color: Color) {
        when (color) {
            Color.GREEN -> println("绿色代表草原")
            Color.BLUE -> println("蓝色代表忧郁")
        }
    }

    fun colorType2(color: Color2) {
        when (color) {
            Color2.GREEN -> println("${color.name},${color.colorName},${color.description}")//GREEN,green,绿色代表草原
            Color2.BLUE -> println("${color.name},${color.colorName},${color.description}")
        }
    }
}