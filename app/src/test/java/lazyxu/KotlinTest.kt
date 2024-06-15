package lazyxu

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import lazyxu.entity.Student
import org.junit.Test
import java.util.concurrent.Executors
import kotlin.properties.Delegates


class KotlinTest {
    var user: String by Delegates.observable("ben") {
            prop, old, new ->
        println("属性${prop.name}的值从: $old -> $new")
    }
    var Student.alias: String
        get() {
            return "$name 有个网名叫ss007"
        }
        set(value) {
            name = value
            println("后来其直接将名字也改成了$value")
        }
    @Test
    fun main22() {
        user="lazyxu"
        println("My name is $user")
        user="chenyingli"
        println("My name is $user")

    }

    @Test
    fun test111(){





        val executors=Executors.newSingleThreadExecutor()
        executors.execute {
            println("")

        }
        executors.submit{

        }


    }


    @Test
    fun maopao() {

        val arr = intArrayOf(5, 3, 7, 6, 4, 1, 6, 2, 9, 10, 8)
        for (i in 0 until arr.size-1) {
            for (j in 0 until arr.size - i - 1) {
                if (arr[j] > arr[j + 1]) { //如果要求按照从大到小排序，只需要改变此处符号
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        println("arr=${arr.contentToString()}")
    }
    @Test
    fun test(){
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L)
            log("World!")
        }
        log("Hello,") // main thread continues here immediately
        runBlocking {     // but this expression blocks the main thread
            delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
        }
    }
    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")


    @Test
    fun main() = runBlocking { // this: CoroutineScope
        val time = System.currentTimeMillis()
        launch {
            delay(4000L)
            println("【${Thread.currentThread().name}】在 runBlocking 里的任务 ${System.currentTimeMillis() - time}")
        }
        coroutineScope { //创建一个协程作用域(会阻塞主线程)
            val test = getTest1()
            val test2 = getTest2()
            println("test=$test,test2=$test2,time=${System.currentTimeMillis() - time}")
            println("【${Thread.currentThread().name}】Task from coroutine scope ${System.currentTimeMillis() - time}") // 这一行会在内嵌 launch 之前输出
        }
        println("【${Thread.currentThread().name}】Coroutine scope is over ${System.currentTimeMillis() - time}") // 这一行在内嵌 launch 执行完毕后才输出
    }

    private suspend fun getTest1() = withContext(Dispatchers.IO) {

        delay(2000L)
        1
    }

    private suspend fun getTest2() = withContext(Dispatchers.IO) {
        delay(2000L)
        2
    }


}






