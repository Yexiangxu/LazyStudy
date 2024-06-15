/**
 * User:Lazy_xu
 * Date:2024/02/22
 * Description:
 * FIXME
 */
object GUtils {
    @JvmStatic
    fun getResourcePrefix(str: String): String {
        // 定义一个函数，用于获取字符串中 "_" 后面的所有字符
        val underscoreIndex = str.indexOf ('_')
        return if (underscoreIndex != -1) {
            str.substring(underscoreIndex + 1)
        } else {// 如果没有找到 "_"，返回空字符串或者你想要的默认值
            str
        }
    }
}