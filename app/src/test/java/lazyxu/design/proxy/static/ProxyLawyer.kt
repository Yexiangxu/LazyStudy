package lazyxu.design.proxy.static

import lazyxu.design.proxy.ILawSuit

/**
 * User:Lazy_xu
 * Date:2024/01/23
 * Description:
 * FIXME
 */
class ProxyLawyer(private val plaintiff: ILawSuit) : ILawSuit {
    override fun submit(proof: String) {
        plaintiff.submit(proof)
    }
    override fun defend() {
        plaintiff.defend()
    }
}