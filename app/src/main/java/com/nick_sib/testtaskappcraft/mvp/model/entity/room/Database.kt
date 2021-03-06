package com.nick_sib.testtaskappcraft.mvp.model.entity.room


import androidx.room.RoomDatabase
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.dao.AlbumDataDao
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.dao.AlbumInfoDao

@androidx.room.Database(
    entities = [AlbumData::class, AlbumInfo::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val albumDataDao: AlbumDataDao
    abstract val albumInfoDao: AlbumInfoDao

    companion object {
        const val DB_NAME = "database.db"

//        var instance: Database? = null
//            get() = field ?: synchronized(Database::class.java) {
//                field = Room.databaseBuilder(App.instance, Database::class.java, DB_NAME)
//                    .fallbackToDestructiveMigration()
//                    .build()
//                field
//            }
//            private set
    }
}