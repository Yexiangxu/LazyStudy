package lazyxu

import org.junit.Test
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JavaTest {
    @Test
    @Throws(Exception::class)
    fun main() {
        val start = System.currentTimeMillis()
        val hashMap = HashMap<Int, String>(16)
        for (i in 0..1000) {
            hashMap[i] = i.toString()
        }
        println("time=${System.currentTimeMillis() - start},$hashMap")
        val start2 = System.currentTimeMillis()
        val hashMap2 = HashMap<Int, String>(1000000)
        for (i in 0..1000) {
            hashMap2[i] = i.toString()
        }
        println("time2=${System.currentTimeMillis() - start2},$hashMap2")

    }

    internal class MyTask(private val taskNum: Int) : Runnable {
        override fun run() {
            println("正在执行task $taskNum")
            try {
                Thread.sleep(4000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            println("task " + taskNum + "执行完毕")
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val queue = ArrayBlockingQueue<Runnable>(5)
            val pool = ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, queue)
            for (i in 0..14) {
                val myTask = MyTask(i)
                pool.execute(myTask)
                println("线程池中的线程数目：" + pool.poolSize + ",队列中等待执行的任务数量：" + pool.queue.size + ",已执行完的任务数目：" + pool.completedTaskCount)
            }
            pool.shutdown()
        }
    }
}
class Singleton private constructor() {
    companion object {
        val instance: Singleton by lazy { Singleton() }
    }
}
