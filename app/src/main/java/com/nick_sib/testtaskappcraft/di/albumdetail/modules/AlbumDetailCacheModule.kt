package com.nick_sib.testtaskappcraft.di.albumdetail.modules

import com.nick_sib.testtaskappcraft.di.albumdetail.AlbumDetailScope
import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.cache.room.RoomAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides

@Module
class AlbumDetailCacheModule {

    @AlbumDetailScope
    @Provides
    fun albumDetailCache(
        database: Database
    ): IAlbumDetailCache = RoomAlbumDetailCache(database)
}