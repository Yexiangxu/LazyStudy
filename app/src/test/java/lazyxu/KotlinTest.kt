package lazyxu

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lazyxu.entity.Student
import org.junit.Test
import java.util.concurrent.Executors
import kotlin.properties.Delegates

public class KotlinTest {
    fun useThreadPool() {
        println("Using ThreadPool")
        val threadPool = Executors.newFixedThreadPool(3) // 创建一个固定大小为3的线程池
        for (i in 1..5) {
            threadPool.submit {
                task(i)
            }
        }
        threadPool.shutdown() // 任务提交完后关闭线程池
    }
    var user: String by Delegates.observable("ben") { prop, old, new ->
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
        user = "lazyxu"
        log("My name is $user")
        user = "chenyingli"
        println("My name is $user")

    }

    @Test
    fun main() = runBlocking {
        GlobalScope.launch(Dispatchers.IO) {
            delay(100)
            log("Task from runBlocking")
        }
        coroutineScope {
            launch {
                delay(500)
                log("Task from nested launch")
            }
            delay(50)
            log("Task from coroutine scope")
        }
        log("Coroutine scope is over")
    }

    private fun log(msg: Any?) = println("[${Thread.currentThread().name}] $msg")
}






