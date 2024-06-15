package com.lazyxu.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class RequestJsonBody {
    private var jsonObject: JSONObject = JSONObject()

    fun get(key: String): String {
        return jsonObject.optString(key, "")
        ApiReuslt.Sucess
    }

    fun remove(key: String) {
        jsonObject.remove(key)
    }

    fun add(key: String, value: Any?): RequestJsonBody = apply {
        value?.let {
            jsonObject.put(key, value)
        }
    }


    fun add(key: String, value: MutableList<Any>?): RequestJsonBody = apply {
        value?.let {
            val jsonArray = JSONArray()
            value.forEach {
                jsonArray.put(it)
            }
            jsonObject.put(key, jsonArray)
        }
    }

    fun add(key: String, jsonArray: JSONArray?): RequestJsonBody = apply {
        jsonArray?.let {
            jsonObject.put(key, jsonArray)
        }
    }


    fun createRequestBody(): RequestBody =
        jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    fun mergeJsonObject(highPriorityJsonObject: JSONObject?): RequestJsonBody =apply{
        try {
            highPriorityJsonObject?.keys()?.forEach { key ->
                jsonObject.put(key, highPriorityJsonObject[key])
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}