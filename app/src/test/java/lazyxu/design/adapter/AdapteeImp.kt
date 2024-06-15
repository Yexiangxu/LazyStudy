package lazyxu.design.adapter

/**
 * User:Lazy_xu
 * Date:2024/01/25
 * Description:
 * FIXME
 */
 open class AdapteeImp :Adaptee{
    override fun d(message: String?, vararg obj: Any?) {
        println("使用新logger记录:message=$message,obj=$obj")
    }
}