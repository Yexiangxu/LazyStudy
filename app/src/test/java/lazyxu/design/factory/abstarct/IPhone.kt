package lazyxu.design.factory.abstarct

/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class IPhone:MobilePhone {
    override fun setOperationSystem() {
        println("苹果手机安装IOS系统");
    }
}