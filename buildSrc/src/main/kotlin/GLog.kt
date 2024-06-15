object GLog {
    const val TAG="GLogTag=== "

    @JvmStatic
    fun d(msg:String){
        println("$TAG$msg")
    }
}