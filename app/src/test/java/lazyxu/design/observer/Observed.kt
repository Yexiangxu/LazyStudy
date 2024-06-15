package lazyxu.design.observer

/**
 * User:Lazy_xu
 * Date:2023/12/06
 * Description:
 * FIXME
 */
class Observed:IObserved {
    private var observedList= mutableListOf<IObserver>()
    override fun register(observer: IObserver) {
        observedList.add(observer)
    }

    override fun unRegister(observer: IObserver) {
        observedList.remove(observer)
    }

    override fun notifyObserver() {
        for (i in observedList){
            i.update()
        }
    }
}