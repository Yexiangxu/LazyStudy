package lazyxu

class User constructor(var id: Int, var name: String, var age: Int) {
    override fun toString(): String {
        return "Student(id=$id, name='$name',age=${age})"
    }
}

//
//class User {
//    companion object {
//        val instance=User()
////        val instance: User by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
////            User() }
//    }
//}