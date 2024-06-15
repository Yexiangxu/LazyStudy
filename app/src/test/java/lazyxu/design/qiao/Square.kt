package lazyxu.design.qiao

/**
 * User:Lazy_xu
 * Date:2024/01/31
 * Description:
 * FIXME
 */
class Square(color: Color) : Shape(color) {
    override fun draw() {
        println("Drawing a square with " + color.fill() + " color.")
    }
}