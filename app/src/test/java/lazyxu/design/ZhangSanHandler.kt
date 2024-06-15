package lazyxu.design

class ZhangSanHandler : Handler {
    override fun AAA(name: String) {
        println("张三AAA")
    }

    override fun afterPropertiesSet() {
        Factory.setHandler("张三", this)
    }
}
