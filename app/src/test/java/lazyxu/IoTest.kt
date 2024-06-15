package lazyxu

import org.junit.Test
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream

class IoTest {
    @Test
    fun main() {
        val outputStream: OutputStream = FileOutputStream("../text.txt")
        val test = "有的人"
        outputStream.write(test.toByteArray())
        outputStream.close()
    }
    @Test
    fun append() {
        val outputStream: OutputStream = FileOutputStream("../text.txt",true)
        val test = "哈哈哈"
        outputStream.write(test.toByteArray())
        outputStream.close()
    }
    @Test
    fun get() {
        val inputStream = FileInputStream("../text.txt")

    }
}