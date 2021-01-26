package com.nick_sib.testtaskappcraft.di.database.modules

import com.nick_sib.testtaskappcraft.di.database.DatabaseScope
import com.nick_sib.testtaskappcraft.mvp.model.cache.room.RoomRepoAlbumsCache
import dagger.Module
import dagger.Provides
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums

@Module
class DatabaseCacheModule {

    @DatabaseScope
    @Provides
    fun databaseCacheModules(
        database: Database
    ): IRepoAlbums = RoomRepoAlbumsCache(database)
}

