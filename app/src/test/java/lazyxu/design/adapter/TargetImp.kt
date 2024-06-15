package lazyxu.design.adapter

/**
 * User:Lazy_xu
 * Date:2024/01/25
 * Description:
 * FIXME
 */
class TargetImp : Target {
    override fun debug(tag: String?, message: String?) {
        println("老logger记录,tag=$tag,message=$message")
    }
}