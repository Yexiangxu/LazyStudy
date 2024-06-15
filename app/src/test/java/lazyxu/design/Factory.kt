package lazyxu.design

object Factory {
    private val map: MutableMap<String, Handler> = HashMap()
    fun getHandler(name: String): Handler? {
        return map[name]
    }

    fun setHandler(name: String?, handler: Handler?) {
        name?.let {
            map.put(name,handler!!)
        }
    }
}
