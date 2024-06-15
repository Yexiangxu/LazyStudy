package lazyxu.design.strategy.traffic

/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */
//乘坐公交车算法
class ByBus : CalculateStrategy {
    override fun calculateTrafficFee(distance: Int): Int {
        return if (distance < 10) 4 else 6
    }
}