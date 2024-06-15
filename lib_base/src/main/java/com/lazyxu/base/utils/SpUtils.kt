package com.lazyxu.base.utils

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * MMKV使用封装
 */
object SpUtils {
    private val mmkv by lazy {
        MMKV.defaultMMKV()
    }

    /**
     * 初始化
     */
    fun initMMKV(context: Context): String = MMKV.initialize(context)

    /**
     * 增、改
     * 根据value类型自动匹配需要执行的方法
     */
    fun put(key: String, value: Any) =
        when (value) {
            is Int -> mmkv.encode(key, value)
            is Long -> mmkv.encode(key, value)
            is Float -> mmkv.encode(key, value)
            is Double -> mmkv.encode(key, value)
            is String -> mmkv.encode(key, value)
            is Boolean -> mmkv.encode(key, value)
            is Parcelable -> mmkv.encode(key, value)
//            is Any-> mmkv.encode(value.javaClass.name, Gson().toJson(value))
            else -> false
        }

    /**
     * 查
     */
    fun getString(key: String, defValue: String): String? =
        mmkv.decodeString(key, defValue)

    fun getInt(key: String, defValue: Int): Int = mmkv.decodeInt(key, defValue)

    fun getLong(key: String, defValue: Long): Long = mmkv.decodeLong(key, defValue)

    fun getDouble(key: String, defValue: Double): Double? =
        mmkv.decodeDouble(key, defValue)

    fun getFloat(key: String, defValue: Float): Float? =
        mmkv.decodeFloat(key, defValue)

    /**
    val v = mmkv.decodeString(key, null as String?)
    if (!TextUtils.isEmpty(v)) {
    return Gson().fromJson(v, key)
    }
     */
    fun <T : Parcelable?> getParcelable(key: String, tClass: Class<T>?): T? =
        mmkv.decodeParcelable(key, tClass)


    /**
     * 默认为false
     */
    fun getBoolean(key: String, defValue: Boolean = false): Boolean =
        mmkv.decodeBool(key, defValue)

    fun contains(key: String): Boolean = mmkv.contains(key)


    /**
     * 删
     */
    fun remove(key: String) = mmkv.removeValueForKey(key)
    fun remove(vararg key: String) = mmkv.removeValuesForKeys(key)
    fun clearAll() = mmkv.clearAll()
}