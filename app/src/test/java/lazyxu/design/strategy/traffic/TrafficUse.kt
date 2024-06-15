package lazyxu.design.strategy.traffic

/**
 * User:Lazy_xu
 * Date:2024/01/17
 * Description:
 * FIXME
 */
class TrafficUse {
    /**
     * 出行费用
     */
    fun tarvelFee(way: String?, distance: Int): Int {
        var trafficFee = 0
        when (way) {
            "bus" -> trafficFee = if (distance < 10) 4 else 6
            "didi" -> trafficFee = if (distance < 3) 8 else 8 + (distance - 3) * 3
            "sharedBicyle" -> trafficFee = 2
            else -> {}
        }
        return trafficFee
    }
}