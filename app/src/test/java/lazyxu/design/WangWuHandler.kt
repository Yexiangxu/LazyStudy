package lazyxu.design

class WangWuHandler : Handler {
    override fun AAA(name: String) {
        println("王五AAA")
    }

    override fun afterPropertiesSet() {
        Factory.setHandler("王五", this)
    }
}
