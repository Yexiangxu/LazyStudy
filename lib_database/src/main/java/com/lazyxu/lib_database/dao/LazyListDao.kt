package com.lazyxu.lib_database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lazyxu.lib_database.constant.ConstantDatabase
import com.lazyxu.lib_database.entity.SearchEntity

/**
 * User:Lazy_xu
 * Date:2024/06/05
 * Description:
 * FIXME
 */
@Dao
interface LazyListDao {

    /**
     * 插入单个数据
     * entity操作的表，OnConflictStrategy冲突策略，
     * ABORT:终止本次操作
     * IGNORE:忽略本次操作，也终止
     * REPLACE:覆盖老数据
     */
    @Insert(entity = SearchEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(searchEntity: SearchEntity): Long

    //    /**
//     * 插入多个数据
//     */
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertList(videoList: MutableList<VideoEntity>)
//
    @Delete
    suspend fun delete(searchEntity: SearchEntity): Int

    @Query("DELETE from ${ConstantDatabase.TABLE_SEARCH}")
    suspend fun delete(): Int
//    /**
//     * 根据id删除数据
//     */
//    @Query("delete from table_video where albumId=:id")
//    suspend fun deleteById(id: Long): Int
//
//    /**
//     * 删除表中所有数据
//     * @return 返回：1 表示有数据删除成功；0:没有数据时
//     */
//    @Query("delete from table_video")
//    suspend fun deleteAllVideo(): Int
//
//
//    /**
//     * 比如 收藏状态、喜爱状态 变化了更新
//     */
//    @Update
//    suspend fun updateVideo(videoEntity: VideoEntity)
//
//    /**
//     * 根据id更新数据
//     */
//    @Query("update table_video set title=:title where albumId=:id")
//    suspend fun updateById(id: Long, title: String)
//
//    @Query("select * from table_video")
//    suspend fun getVideo(): VideoEntity?
//
//    @Query("select * from table_video where albumId=:id")
//    suspend fun getVideoById(id: Long): VideoEntity?
//
//    @Query("select isCollectTag from table_video where uid=:uid and albumId=:album_id")
//    suspend fun isCollect(uid: Int, album_id: Long): Int//1收藏，0未收藏
//
    /**
     * 查找数据库的全部内容
     */
    @Query("select * from ${ConstantDatabase.TABLE_SEARCH} order by createTime desc")
    suspend fun getAllSearch(): MutableList<SearchEntity>?

    //
//    /**
//     * 第一页数据：limit = 10, offset = 0
//     * 第二页数据：limit = 10, offset = 10
//     * 第三页数据：limit = 10, offset = 20
//     * mLimit设置页数，offset设置第几页
//     */
//    @Query("select * from table_video where uid = :uid and isRecordTag=:record order by createTime desc limit :mLimit offset :mOffSet")
//    suspend fun getAllByUid(uid: Int,record:Int,
//                    mLimit: Int? = 3,
//                    mOffSet: Int = 0): MutableList<VideoEntity>  //查找用户下面的所有作品列表
//
//    /**
//     * 一旦发生了insert，update，delete，room会自动读取表中最新的数据，发送给UI层，刷新页面
//     * 不要使用MutableLiveData和suspend 会报错
//     */
    @Query("select * from table_search")
    fun allSearchLiveData(): LiveData<List<SearchEntity>?>

}