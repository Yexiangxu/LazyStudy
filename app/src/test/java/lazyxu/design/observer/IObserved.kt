package lazyxu.design.observer

/**
 * User:Lazy_xu
 * Date:2023/12/06
 * Description:被观察者
 * FIXME
 */
interface IObserved {
    fun register(observer:IObserver)
    fun unRegister(observer: IObserver)

    fun notifyObserver()
}