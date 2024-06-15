package lazyxu.design.factory.abstarct

/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class MiPhone:MobilePhone {
    override fun setOperationSystem() {
        println("小米手机安装Android系统");
    }
}