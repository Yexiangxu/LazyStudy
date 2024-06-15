//package com.lazyxu.base.save
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.SharedPreferencesMigration
//import androidx.datastore.preferences.core.*
//import androidx.datastore.preferences.preferencesDataStore
//import com.lazyxu.base.base.BaseApplication
//
//import com.lazyxu.base.save.DataStoreUtils.clear
//import com.lazyxu.base.save.DataStoreUtils.clearSync
//import com.lazyxu.base.save.DataStoreUtils.getData
//import com.lazyxu.base.save.DataStoreUtils.getSyncData
//import com.lazyxu.base.save.DataStoreUtils.putData
//import com.lazyxu.base.save.DataStoreUtils.putSyncData
//import com.lazyxu.base.save.DataStoreUtils.readBooleanData
//import com.lazyxu.base.save.DataStoreUtils.readBooleanFlow
//import com.lazyxu.base.save.DataStoreUtils.readFloatData
//import com.lazyxu.base.save.DataStoreUtils.readFloatFlow
//import com.lazyxu.base.save.DataStoreUtils.readIntData
//import com.lazyxu.base.save.DataStoreUtils.readIntFlow
//import com.lazyxu.base.save.DataStoreUtils.readLongData
//import com.lazyxu.base.save.DataStoreUtils.readLongFlow
//import com.lazyxu.base.save.DataStoreUtils.readStringData
//import com.lazyxu.base.save.DataStoreUtils.readStringFlow
//import com.lazyxu.base.save.DataStoreUtils.saveBooleanData
//import com.lazyxu.base.save.DataStoreUtils.saveFloatData
//import com.lazyxu.base.save.DataStoreUtils.saveIntData
//import com.lazyxu.base.save.DataStoreUtils.saveLongData
//import com.lazyxu.base.save.DataStoreUtils.saveStringData
//import com.lazyxu.base.save.DataStoreUtils.saveSyncBooleanData
//import com.lazyxu.base.save.DataStoreUtils.saveSyncFloatData
//import com.lazyxu.base.save.DataStoreUtils.saveSyncIntData
//import com.lazyxu.base.save.DataStoreUtils.saveSyncLongData
//import com.lazyxu.base.save.DataStoreUtils.saveSyncStringData
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.runBlocking
//import java.io.IOException
//
///**
// *
// * 异步获取数据
// * [getData] [readBooleanFlow] [readFloatFlow] [readIntFlow] [readLongFlow] [readStringFlow]
// * 同步获取数据
// * [getSyncData] [readBooleanData] [readFloatData] [readIntData] [readLongData] [readStringData]
// *
// * 异步写入数据
// * [putData] [saveBooleanData] [saveFloatData] [saveIntData] [saveLongData] [saveStringData]
// * 同步写入数据
// * [putSyncData] [saveSyncBooleanData] [saveSyncFloatData] [saveSyncIntData] [saveSyncLongData] [saveSyncStringData]
// *
// * 异步清除数据
// * [clear]
// * 同步清除数据
// * [clearSync]
// *
// * 描述：DataStore 工具类
// */
//object DataStoreUtils {
//    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
//        name = BaseApplication.INSTANCE.packageName + "_preferences",
//        produceMigrations = { context ->
//            listOf(SharedPreferencesMigration(context, BaseApplication.INSTANCE.packageName + "_preferences"))
//        })
//    val dataStore: DataStore<Preferences> = BaseApplication.INSTANCE.dataStore
//
//    @Suppress("UNCHECKED_CAST")
//    fun <U> getSyncData(key: String, default: U): U {
//        val res = when (default) {
//            is Long -> readLongData(key, default)
//            is String -> readStringData(key, default)
//            is Int -> readIntData(key, default)
//            is Boolean -> readBooleanData(key, default)
//            is Float -> readFloatData(key, default)
//            else -> throw IllegalArgumentException("This type can be saved into DataStore")
//        }
//        return res as U
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    fun <U> getData(key: String, default: U): Flow<U> {
//        val data = when (default) {
//            is Long -> readLongFlow(key, default)
//            is String -> readStringFlow(key, default)
//            is Int -> readIntFlow(key, default)
//            is Boolean -> readBooleanFlow(key, default)
//            is Float -> readFloatFlow(key, default)
//            else -> throw IllegalArgumentException("This type can be saved into DataStore")
//        }
//        return data as Flow<U>
//    }
//
//    suspend fun <U> putData(key: String, value: U) {
//        when (value) {
//            is Long -> saveLongData(key, value)
//            is String -> saveStringData(key, value)
//            is Int -> saveIntData(key, value)
//            is Boolean -> saveBooleanData(key, value)
//            is Float -> saveFloatData(key, value)
//            else -> throw IllegalArgumentException("This type can be saved into DataStore")
//        }
//    }
//
//    fun <U> putSyncData(key: String, value: U) {
//        when (value) {
//            is Long -> saveSyncLongData(key, value)
//            is String -> saveSyncStringData(key, value)
//            is Int -> saveSyncIntData(key, value)
//            is Boolean -> saveSyncBooleanData(key, value)
//            is Float -> saveSyncFloatData(key, value)
//            else -> throw IllegalArgumentException("This type can be saved into DataStore")
//        }
//    }
//
//    private fun readBooleanFlow(key: String, default: Boolean = false): Flow<Boolean> =
//        dataStore.data.catch {
//            //当读取数据遇到错误时，如果是 `IOException` 异常，发送一个 emptyPreferences 来重新使用
//            //但是如果是其他的异常，最好将它抛出去，不要隐藏问题
//            if (it is IOException) {
//                it.printStackTrace()
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }.map {
//            it[booleanPreferencesKey(key)] ?: default
//        }
//
//    private fun readBooleanData(key: String, default: Boolean = false): Boolean {
//        var value = false
//        runBlocking {
//            dataStore.data.first {
//                value = it[booleanPreferencesKey(key)] ?: default
//                true
//            }
//        }
//        return value
//    }
//
//    private fun readIntFlow(key: String, default: Int = 0): Flow<Int> =
//        dataStore.data.catch {
//            if (it is IOException) {
//                it.printStackTrace()
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }.map {
//            it[intPreferencesKey(key)] ?: default
//        }
//
//    private fun readIntData(key: String, default: Int = 0): Int {
//        var value = 0
//        runBlocking {
//            dataStore.data.first {
//                value = it[intPreferencesKey(key)] ?: default
//                true
//            }
//        }
//        return value
//    }
//
//    private fun readStringFlow(key: String, default: String = ""): Flow<String> =
//        dataStore.data.catch {
//            if (it is IOException) {
//                it.printStackTrace()
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }.map {
//            it[stringPreferencesKey(key)] ?: default
//        }
//
//    private fun readStringData(key: String, default: String = ""): String {
//        var value = ""
//        runBlocking {
//            dataStore.data.first {
//                value = it[stringPreferencesKey(key)] ?: default
//                true
//            }
//        }
//        return value
//    }
//
//    private fun readFloatFlow(key: String, default: Float = 0f): Flow<Float> =
//        dataStore.data.catch {
//            if (it is IOException) {
//                it.printStackTrace()
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }.map {
//            it[floatPreferencesKey(key)] ?: default
//        }
//
//    private fun readFloatData(key: String, default: Float = 0f): Float {
//        var value = 0f
//        runBlocking {
//            dataStore.data.first {
//                value = it[floatPreferencesKey(key)] ?: default
//                true
//            }
//        }
//        return value
//    }
//
//    private fun readLongFlow(key: String, default: Long = 0L): Flow<Long> =
//        dataStore.data.catch {
//            if (it is IOException) {
//                it.printStackTrace()
//                emit(emptyPreferences())
//            } else {
//                throw it
//            }
//        }.map {
//            it[longPreferencesKey(key)] ?: default
//        }
//
//    private fun readLongData(key: String, default: Long = 0L): Long {
//        var value = 0L
//        runBlocking {
//            dataStore.data.first {
//                value = it[longPreferencesKey(key)] ?: default
//                true
//            }
//        }
//        return value
//    }
//
//    private suspend fun saveBooleanData(key: String, value: Boolean) {
//        dataStore.edit { mutablePreferences ->
//            mutablePreferences[booleanPreferencesKey(key)] = value
//        }
//    }
//
//    private fun saveSyncBooleanData(key: String, value: Boolean) =
//        runBlocking { saveBooleanData(key, value) }
//
//    private suspend fun saveIntData(key: String, value: Int) {
//        dataStore.edit { mutablePreferences ->
//            mutablePreferences[intPreferencesKey(key)] = value
//        }
//    }
//
//    private fun saveSyncIntData(key: String, value: Int) = runBlocking { saveIntData(key, value) }
//
//    private suspend fun saveStringData(key: String, value: String) {
//        dataStore.edit { mutablePreferences ->
//            mutablePreferences[stringPreferencesKey(key)] = value
//        }
//    }
//
//    private fun saveSyncStringData(key: String, value: String) =
//        runBlocking { saveStringData(key, value) }
//
//    private suspend fun saveFloatData(key: String, value: Float) {
//        dataStore.edit { mutablePreferences ->
//            mutablePreferences[floatPreferencesKey(key)] = value
//        }
//    }
//
//    private fun saveSyncFloatData(key: String, value: Float) =
//        runBlocking { saveFloatData(key, value) }
//
//    suspend fun saveLongData(key: String, value: Long) {
//        dataStore.edit { mutablePreferences ->
//            mutablePreferences[longPreferencesKey(key)] = value
//        }
//    }
//
//    private fun saveSyncLongData(key: String, value: Long) =
//        runBlocking { saveLongData(key, value) }
//
//    suspend fun clear() {
//        dataStore.edit {
//            it.clear()
//        }
//    }
//
//    fun clearSync() {
//        runBlocking {
//            dataStore.edit {
//                it.clear()
//            }
//        }
//    }
//
//}