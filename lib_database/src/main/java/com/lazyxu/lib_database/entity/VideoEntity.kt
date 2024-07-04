package com.lazyxu.lib_database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * User:Lazy_xu
 * Date:2024/06/05
 * Description:
 * FIXME
 */
@Parcelize
@Entity(tableName = "table_video")//如果没设置tableName则默认为该类的命名
data class VideoEntity(
    //必须至少要有一个主键，不能为空，autoGenerate表示cache_key的值是否由数据库自动生成
    @PrimaryKey
    var id: Long,
    var title: String,
    var desc: String,
    var play_url: String,
    var author_name: String,
    var image_url: String,
    var collect_count: Int,//喜爱数量
    var is_record: Int,//0未播放，1已播放
    var is_collect: Int,//0未收藏，1已收藏
    var create_time: Long
) : Parcelable