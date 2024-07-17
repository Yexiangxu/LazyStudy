package lazyxu

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
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
    fun main() = runBlocking{
        coroutineScope {
            println("开始 ${System.currentTimeMillis()}")
            CoroutineScope(Dispatchers.IO).launch {

            }
            CoroutineScope(Dispatchers.IO).launch {
                delay(1000)
                println("bbb ${System.currentTimeMillis()}")
            }
            println("结束 ${System.currentTimeMillis()}")

            delay(5000)
        }

    }
    suspend fun test1(){
        delay(1000)
        println("aaa ${System.currentTimeMillis()}")
    }




}






