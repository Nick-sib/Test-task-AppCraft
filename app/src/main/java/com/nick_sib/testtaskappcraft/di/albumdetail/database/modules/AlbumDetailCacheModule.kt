package com.nick_sib.testtaskappcraft.di.albumdetail.database.modules

import com.nick_sib.testtaskappcraft.di.albumdetail.database.AlbumDetailDatabaseScope
import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.cache.room.RoomAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides

@Module
class AlbumDetailCacheModule {

    @AlbumDetailDatabaseScope
    @Provides
    fun albumDetailCache(
        database: Database
    ): IAlbumDetailCache = RoomAlbumDetailCache(database)
}