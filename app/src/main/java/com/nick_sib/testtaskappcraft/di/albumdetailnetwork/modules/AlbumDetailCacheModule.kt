package com.nick_sib.testtaskappcraft.di.albumdetailnetwork.modules

import com.nick_sib.testtaskappcraft.di.albumdetailnetwork.AlbumDetailNetworkScope
import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.cache.room.RoomAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides

@Module
class AlbumDetailCacheModule {

    @AlbumDetailNetworkScope
    @Provides
    fun albumDetailCache(
        database: Database
    ): IAlbumDetailCache = RoomAlbumDetailCache(database)
}