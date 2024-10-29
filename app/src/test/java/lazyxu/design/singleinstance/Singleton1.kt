package lazyxu.design.singleinstance

/**
 * User:Lazy_xu
 * Date:2023/12/05
 * Description:
 * FIXME
 */


class Singleton36 constructor() {
    companion object {
        val singleton3 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton36()
        }
    }
}



object Singleton1{}

class Singleton2{
    companion object{
        @Volatile
        var instance:Singleton2?=null
        fun getInstance():Singleton2{
            if (instance==null){
                synchronized(Singleton2::class.java){
                    if (instance==null){
                        instance= Singleton2()
                    }
                }
            }
            return instance!!
        }
    }
}
class Singleton3{

    companion object{
        fun getInstance():Singleton3{
            return Holder.HOLDER
        }
    }
    private object Holder{
        val HOLDER=Singleton3()
    }
}
enum class Singleton4{
    INSTANCE;
    fun doSomeThing(){}
}

