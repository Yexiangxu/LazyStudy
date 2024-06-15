package com.lazyxu.base.utils

import android.content.Context
import android.util.Log
import java.io.*

/**
 * [writeFile]
 * https://blog.csdn.net/htwhtw123/article/details/72493301
 */
class FileUtils {
    /**
     * 写
     */
    fun writeFile(path: String, msg: String) {
        try {
            FileOutputStream(File(path)).use {
                it.write(msg.toByteArray())
            }
        } catch (e: Exception) {
        }
    }

    fun writeFileAppend(path: String, conent: String) {
        try {
            FileOutputStream(File(path), true).use { fileOutputStream ->
                OutputStreamWriter(fileOutputStream).use { outputStreamWriter ->
                    BufferedWriter(outputStreamWriter).use { bufferedWriter ->
                        bufferedWriter.write(conent)
                    }
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * use函数会自动关闭调用者（无论中间是否出现异常）
     */
    fun getAssetString(context: Context, fileName: String) {
        try {
            context.assets.open(fileName).use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    BufferedReader(reader).use { bufferedReader ->
                        val result = StringBuilder()
                        var temp: String?
                        while (bufferedReader.readLine().also { temp = it } != null) {
                            result.append(temp)
                        }
                        Log.i("AssetTag", "result:$result")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}