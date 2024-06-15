package lazyxu.java.createobject

import com.lazyxu.lazystudy.test.Person
import com.lazyxu.lazystudy.test.Person1
import org.junit.Test

/**
 * User:Lazy_xu
 * Date:2023/12/05
 * Description:
 * FIXME
 */
class MainUse {
    @Test
    fun main() {
//        val person=Person()


        try {
            val personClass = Class.forName("com.lazyxu.lazystudy.test.Person")
            val person = personClass.newInstance() as Person?
        } catch (t: Throwable){
            t.printStackTrace()
        }

        


    }
    @Test
    fun clone(){

        val person1 = Person1("Alice", 25, mutableListOf("Reading", "Traveling"))

        // 执行浅拷贝
        val person2 = person1.copy()

        // 修改原始对象的可变属性
        person2.hobbies.add("Swimming")

        println(person1.hobbies)    // 输出 Person(name=Alice, age=25, hobbies=[Reading, Traveling, Swimming])
        println(person2.hobbies)    // 输出 Person(name=Alice, age=25, hobbies=[Reading, Traveling, Swimming])

        val person3 = Person("Alice", 25)
        // 执行浅拷贝
        val person4 = person3.copy()
        // 修改原始对象的可变属性
        person3.name="Swimming"
        println(person3)    // 输出 Person(name=Swimming, age=25)
        println(person4)    // 输出 Person(name=Alice, age=25)
    }

}

