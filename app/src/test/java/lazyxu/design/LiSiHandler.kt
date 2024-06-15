package lazyxu.design

class LiSiHandler : Handler {
    override fun AAA(name: String) {
        println("李四AAA")
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        Factory.setHandler("李四", this)
    }
}
