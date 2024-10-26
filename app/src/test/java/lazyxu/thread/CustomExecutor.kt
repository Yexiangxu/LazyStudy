package lazyxu.thread

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class CustomExecutor :ExecutorService{
    override fun execute(command: Runnable?) {
    }

    override fun shutdown() {
    }

    override fun shutdownNow(): MutableList<Runnable> {
    }

    override fun isShutdown(): Boolean {
    }

    override fun isTerminated(): Boolean {
    }

    override fun awaitTermination(timeout: Long, unit: TimeUnit?): Boolean {
    }

    override fun <T : Any?> submit(task: Callable<T>?): Future<T> {
    }

    override fun <T : Any?> submit(task: Runnable?, result: T): Future<T> {
    }

    override fun submit(task: Runnable?): Future<*> {
    }

    override fun <T : Any?> invokeAll(tasks: MutableCollection<out Callable<T>>?): MutableList<Future<T>> {
    }

    override fun <T : Any?> invokeAll(
        tasks: MutableCollection<out Callable<T>>?,
        timeout: Long,
        unit: TimeUnit?
    ): MutableList<Future<T>> {
    }

    override fun <T : Any?> invokeAny(tasks: MutableCollection<out Callable<T>>?): T {
    }

    override fun <T : Any?> invokeAny(
        tasks: MutableCollection<out Callable<T>>?,
        timeout: Long,
        unit: TimeUnit?
    ): T {
    }
}