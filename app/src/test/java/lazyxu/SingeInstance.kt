package lazyxu

class SingeInstance {
    companion object {
        val instance: SingeInstance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            SingeInstance()
        }
    }
}
