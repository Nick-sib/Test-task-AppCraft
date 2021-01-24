package com.nick_sib.testtaskappcraft.mvp.model.entity.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.dao.AlbumDataDao
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.dao.AlbumInfoDao

@androidx.room.Database(
    entities = [AlbumData::class, AlbumInfo::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: AlbumDataDao
    abstract val repositoryDao: AlbumInfoDao

    companion object {
        private const val DB_NAME = "database.db"
        var instance: Database? = null
            get() = field ?: synchronized(Database::class.java) {
                field = Room.databaseBuilder(App.instance, Database::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                field
            }
            private set
    }
}