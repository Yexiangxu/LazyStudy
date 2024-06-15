package lazyxu.design.singleinstance

/**
 * User:Lazy_xu
 * Date:2023/12/05
 * Description:
 * FIXME
 */
object Singleton1 {
    fun doSomeSing() {}
}

class Singleton2 constructor() {
    companion object {
        val singleton2 = Singleton2()
    }

    fun doSomeSing() {}
}

class Singleton3 constructor() {
    companion object {
        val singleton3 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton3()
        }
    }
}

enum class Singleton4 {
    SINGLETON4;

    fun doSomeSing() {}
}

class Singleton5 {
    companion object{
        val singleton5=SingletonHolder.holder
    }
    private object SingletonHolder{
        val holder=Singleton5()
    }
}

