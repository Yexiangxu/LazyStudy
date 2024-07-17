package com.lazyxu.base.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object AssetsUtils {

    fun loadListFromAsset(context: Context, filePath: String): List<String> {
        var inputstreamreader: BufferedReader? = null
        try {
            val input = context.assets.open(filePath)
            inputstreamreader = BufferedReader(InputStreamReader(input))
            return inputstreamreader.readLines()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputstreamreader?.close()
        }
        return mutableListOf<String>()
    }

    fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}