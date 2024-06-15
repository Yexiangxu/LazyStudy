package lazyxu.design.factory

import lazyxu.design.factory.Computer

/**
 * User:Lazy_xu
 * Date:2024/01/18
 * Description:
 * FIXME
 */
class MiComputer : Computer {
    override fun setOperationSystem() {
        println("小米笔记本安装Win10系统")
    }
}