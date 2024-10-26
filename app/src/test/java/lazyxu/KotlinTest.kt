package lazyxu

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import lazyxu.entity.Student
import org.junit.Test
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

public class KotlinTest {
    private fun log(msg: Any?) = println("========[${Thread.currentThread().name}] $msg")


    @Test
    fun main11() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = getData()
            log(data)
        }
        Thread.sleep(3000)
        log("end")
    }

    private suspend fun getData() :Int {
        delay(1000)
        return 100
    }
    @Test
    fun main10() = runBlocking {
        // 1. 创建一个大小为 4 的固定线程池
        val myThreadPool = Executors.newFixedThreadPool(4).asCoroutineDispatcher()

        // 2. 启动协程并指定调度器
        launch(myThreadPool) {
            println("Running on custom thread pool: ${Thread.currentThread().name}")
            delay(500)
            println("Task completed on: ${Thread.currentThread().name}")
        }

        // 3. 再次启动协程来观察线程复用情况
        launch(myThreadPool) {
            println("Running second task on: ${Thread.currentThread().name}")
            delay(500)
            println("Second task completed on: ${Thread.currentThread().name}")
        }
        println("Second task completed on: ${Thread.currentThread().name}")
        // 4. 关闭线程池，释放资源
        myThreadPool.close()
    }

    @Test
    fun main() {
        val fixedThreadPool = Executors.newFixedThreadPool(4)// 创建一个拥有 4 个线程的固定线程池
        for (i in 1..10) {
            fixedThreadPool.execute {// 提交任务到线程池
                println("${Thread.currentThread().name} 执行任务")
            }
        }
        fixedThreadPool.shutdown()
        println("线程池已关闭，等待任务完成")
        try {
            // 等待线程池中的任务完成，最多等待5秒
            if (!fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                println("任务未在5秒内完成，尝试强制停止")
                fixedThreadPool.shutdownNow() // 强制停止所有任务
            }
        } catch (e: InterruptedException) {
            println("等待线程池关闭时被中断，强制停止")
            fixedThreadPool.shutdownNow()
        }
        // 检查线程池是否已经关闭
        if (fixedThreadPool.isTerminated) {
            println("线程池已成功停止")
        } else {
            println("线程池未完全停止")
        }


    }
}






