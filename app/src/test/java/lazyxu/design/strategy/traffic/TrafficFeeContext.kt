package lazyxu.design.strategy.traffic

/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */
class TrafficFeeContext {
    fun tarvelFee(strategy: CalculateStrategy, distance: Int): Int {
        return strategy.calculateTrafficFee(distance)
    }
}