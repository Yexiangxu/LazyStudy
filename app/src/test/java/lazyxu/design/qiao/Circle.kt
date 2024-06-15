package lazyxu.design.qiao

/**
 * User:Lazy_xu
 * Date:2024/01/31
 * Description:
 * FIXME
 */
class Circle(color: Color) : Shape(color) {
    override fun draw() {
        println("Drawing a circle with " + color.fill() + " color.")
    }
}