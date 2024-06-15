package lazyxu.design.strategy.traffic

/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */
//乘坐滴滴快车算法
class ByDiDi:CalculateStrategy {
    override fun calculateTrafficFee(distance: Int): Int {
        return if (distance < 3) 8 else 8 + (distance - 3) * 3
    }
}