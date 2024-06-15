package com.lazyxu.lib_database.manager

import com.lazyxu.lib_database.database.LazyDataBase
import com.lazyxu.lib_database.entity.SearchEntity

/**
 * User:Lazy_xu
 * Date:2024/06/05
 * Description:
 * FIXME
 */
object LazyListDaoManager {
    private val lazyListDao by lazy { LazyDataBase.getInstance().videoListDao() }

    suspend fun insertSearch(searchEntity: SearchEntity): Long {
        return lazyListDao.insertSearch(searchEntity)
    }

    suspend fun getAllSearch(): MutableList<SearchEntity>? {
        return lazyListDao.getAllSearch()
    }

    suspend fun delete(): Int {
        return lazyListDao.delete()
    }
}