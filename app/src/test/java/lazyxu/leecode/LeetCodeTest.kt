package lazyxu.leecode

import org.junit.Test


/**
 * User:Lazy_xu
 * Date:2024/01/11
 * Description:
 * FIXME
 */

class LeetCodeTest {


    fun plusOne(digits: IntArray): IntArray {


        var newArray = digits

        for (i in digits.size - 1 downTo 0) {
            if (digits[i] != 9) {
                newArray[i] = digits[i] + 1
                break
            } else {
                if (i == 0) {

                }

            }
        }
        return newArray
    }

    /**
    输入：digits = [1,2,3]
    输出：[1,2,4]
    解释：输入数组表示数字 123。
    示例 2：

    输入：digits = [4,3,2,1]
    输出：[4,3,2,2]
    解释：输入数组表示数字 4321。
    示例 3：

    输入：digits = [0]
     */
    @Test
    fun main() {
        val prices = intArrayOf(9, 8, 7, 6, 5, 4)
        println(plusOne(prices).joinToString(", "))
    }
}