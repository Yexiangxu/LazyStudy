package lazyxu

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lazyxu.entity.Student
import org.junit.Test
import kotlin.properties.Delegates

public class KotlinTest {

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
        launch(Dispatchers.IO) {
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






