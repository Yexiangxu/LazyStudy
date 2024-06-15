package com.lazyxu.lib_database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lazyxu.lib_database.constant.ConstantDatabase
import kotlinx.parcelize.Parcelize

/**
 * User:Lazy_xu
 * Date:2024/06/06
 * Description: kotlinx.android.parcel.Parcelize 已停止更新，官方推荐迁移到 kotlinx.parcelize.Parcelize
 * FIXME
 */
@Parcelize
@Entity(tableName = ConstantDatabase.TABLE_SEARCH)//如果没设置tableName则默认为该类的命名
data class SearchEntity(
    //必须至少要有一个主键，不能为空，autoGenerate表示Room会自动生成一个唯一的uid值，而不需要开发者手动设置这个值
    @PrimaryKey
    var keyTag: String,
    var createTime: Long = System.currentTimeMillis()
) : Parcelable