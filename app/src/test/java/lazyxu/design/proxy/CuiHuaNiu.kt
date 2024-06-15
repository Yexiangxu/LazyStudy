package lazyxu.design.proxy

/**
 * User:Lazy_xu
 * Date:2024/01/19
 * Description:
 * FIXME
 */
class CuiHuaNiu : ILawSuit {
    override fun submit(proof: String) {
        println("老板欠薪跑路，证据如下：$proof")
    }
    override fun defend() {
        println("铁证如山，马旭还牛翠花血汗钱")
    }
}