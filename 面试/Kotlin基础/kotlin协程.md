1. 协程是什么
    - 一个解决**回调地狱**方便处理并发的线程框架，类似线程池、AsyncTask，可以用同步形式代码写出异步效果
2. 协程和线程关系
    - 协程是运行在线程上的一个任务单元，是一种轻量级线程
3. [协程挂起与恢复](#suspend)
    - 挂起的是**协程**，不是挂起线程，也不是挂起函数
    - 当线程执行到`suspend`函数的地方，不会继续执行当前协程的代码了，所以它不会阻塞线程，是 **非阻塞式挂起**
4. 使用场景
    1. 需要拿到多个异步任务执行返回的结果最后一起进行相关操作
    2. 一个异步任务依赖于上一个异步任务
    3. 当前正在执行一项异步任务，但是你突然不想要它执行了，随时可以取消
    4. 如果你想让一个任务最多执行3秒，超过3秒则自动取消
5. [协程使用详解](#use_detail)
    - [启动协程](#launch)
    - 作用域

### <span id = "suspend">协程挂起与恢复</span>

```agsl
interface Continuation<in T> {
    val context: CoroutineContext
    fun resumeWith(result: Result<T>)
}
```

### <span id = "use_detail">协程使用详解</span>

- `SupervisorJob()`:中的每个子协程都是独立的，一个子协程的失败不会传播到其他子协程
- Dispatchers.Main.immediate

#### 启动协程

1. `runBlocking{}` :仅供单元测试用，实际业务开发不会用，因为它是线程阻塞的
2. `GlobalScope.launch {}`
3. `CoroutineScope(context:CoroutineContext).launch {}`

第三种通过 `context:CoroutineContext`不同衍生平时业务中用到如下

```agsl
MainScope().launch {} = CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {}
```

```agsl
viewModelScope.launch {}
```
>从源码中可知 `viewModelScope` 的 `context`为 `SupervisorJob() + Dispatchers.Main.immediate`，并且跟`ViewModel`生命周期绑定,当`ViewModel` `clear` 的时候
> 会执行 `coroutineContext.cancel()`
>

```agsl
lifecycleScope.launch {}
```




- `GlobalScope` 全局作用域



### <span id = "suspend">协程挂起与恢复</span>

   ```agsl
   interface Continuation<in T> {
       val context: CoroutineContext
       fun resumeWith(result: Result<T>)
   }
   ```

### <span id = "use_detail">协程使用详解</span>

- `SupervisorJob()`:中的每个子协程都是独立的，一个子协程的失败不会传播到其他子协程
- Dispatchers.Main.immediate

### suspend函数（协程的核心）
1. 作用：`suspend`函数让协程在执行挂起操作时，暂时“挂起”自己，释放执行资源而不会阻塞线程。能够暂停并在合适的时候恢复执行


### Dispatchers调度器

1. Dispatchers.Main：主线程
2. Dispatchers.IO：子线程。用于 I/O 密集型任务（如文件读写、数据库操作、网络请求等）
3. Dispatchers.Default：子线程。用于 CPU 密集型任务（如复杂的数学计算、数据处理、排序等）