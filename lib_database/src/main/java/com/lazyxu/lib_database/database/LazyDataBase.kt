package com.lazyxu.lib_database.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lazyxu.base.base.BaseApplication
import com.lazyxu.lib_database.dao.LazyListDao
import com.lazyxu.lib_database.entity.SearchEntity


/**
 * User:Lazy_xu
 * Date:2024/06/05
 * Description:
 * FIXME
 */
/**
 *exportSchema 是一个非常有用的选项，可以帮助你管理和维护数据库架构的历史记录。这个选项允许 Room 在编译时将数据库的架构信息导出到一个 JSON 文件中，这对于调试、版本控制以及迁移策略的编写都是非常有帮助
 * bulid.gradle中配置  arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
 * 生成文件路径：当前使用module中和src文件并列生成一个 schemas 文件夹，里面有一个 1.json (1即版本号)
 */
@Database(
    entities = [//VideoEntity::class,
        SearchEntity::class], version = 1, exportSchema = true
)
//@TypeConverters(TypeResponseConverter::class)
abstract class LazyDataBase() : RoomDatabase() {

    //抽象方法或者抽象类标记
    abstract fun videoListDao(): LazyListDao

    companion object {
        @Volatile
        private var INSTANCE: LazyDataBase? = null

        @Synchronized
        fun getInstance(): LazyDataBase {
            return INSTANCE ?: Room.databaseBuilder(
                BaseApplication.INSTANCE,
                LazyDataBase::class.java,
                "lazyxu_db"
            )
                //是否允许在主线程查询，默认是false
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()//当数据库版本号改变（例如从 1 到 2），而没有提供相应的迁移策略时，Room 将删除现有的数据库表并重新创建
//                .addTypeConverter(typeResponseConverter)
                .build()
        }
    }

}