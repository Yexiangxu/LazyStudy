package lazyxu.jvm

import org.junit.Test

/**
 * User:Lazy_xu
 * Date:2024/04/17
 * Description:
 * FIXME
 */
 class KotlinUse {
    private var map= mapOf<String,String>("1" to "12","2" to "23")


    @Test
    fun main12() {
        // 装箱：将基本数据类型转换为对应的包装类
        val primitiveInt: Int = 10
        val boxedInt: Int? = primitiveInt // 自动装箱
        println("Boxed Integer: $boxedInt")

        // 拆箱：将包装类转换为对应的基本数据类型
        val unboxedInt: Int = boxedInt!! // 强制拆箱
        println("Unboxed int: $unboxedInt")

        // 自动装箱：Kotlin 中没有显示的自动装箱语法，使用装箱操作符时会自动执行装箱
        val autoBoxedInt: Int? = primitiveInt
        println("Auto-boxed Integer: $autoBoxedInt")

        // 自动拆箱：Kotlin 中没有显式的自动拆箱语法，使用拆箱操作符时会自动执行拆箱
        val autoUnboxedInt: Int = autoBoxedInt!!
        println("Auto-unboxed int: $autoUnboxedInt")
    }


}