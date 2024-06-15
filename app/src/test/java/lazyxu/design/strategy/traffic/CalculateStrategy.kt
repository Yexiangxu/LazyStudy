package lazyxu.design.strategy.traffic

/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */
interface CalculateStrategy {
    fun calculateTrafficFee(distance: Int): Int
}