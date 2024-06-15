package lazyxu.design.strategy.traffic

/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */
//骑共享单车算法
class BySharedBicycle : CalculateStrategy {
    override fun calculateTrafficFee(distance: Int): Int {
        return 2
    }
}